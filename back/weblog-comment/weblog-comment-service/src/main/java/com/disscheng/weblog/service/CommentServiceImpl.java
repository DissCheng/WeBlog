package com.disscheng.weblog.service;


import com.alibaba.fastjson2.JSON;
import com.disscheng.weblog.api.CommentService;
import com.disscheng.weblog.context.BaseContext;
import com.disscheng.weblog.mapper.CommentMapper;
import com.disscheng.weblog.pojo.dto.CommentQueryDto;
import com.disscheng.weblog.pojo.rq.CommentAddRq;
import com.disscheng.weblog.pojo.entity.Comment;
import com.disscheng.weblog.pojo.rq.CommentQueryRq;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@DubboService
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String REDIS_COMMENT_KEY = "weblog:comment:";

    private static final String REDIS_COMMENT_REPLIES_KEY = "weblog:comment:relies:";

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addComment(CommentAddRq commentAddRq) {
        //先更新数据库
        Comment c = Comment.builder()
                .articleId(commentAddRq.getArticleId())
                .toAuthorId(commentAddRq.getToAuthorId())
                .toAuthorName(commentAddRq.getToAuthorName())
                .authorId(BaseContext.getUserId())
                .authorName(commentAddRq.getAuthorName())
                .replyId(commentAddRq.getReplyId())
                .rootId(commentAddRq.getRootId())
                .content(commentAddRq.getContent())
                .isPrimary(commentAddRq.getIsPrimary())
                .likes(0L)
                .isDeleted(false)
                .replies(0L)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        commentMapper.insertComment(c);
        commentMapper.updateCommentReplies(
                Comment.builder()
                        .id(commentAddRq.getRootId())
                        .build()
        );
        //TODO 评论的ID需要统一获取而不是数据库自增，现在要访问数据库获取评论ID,非常不合理
        if (c.getIsPrimary()) {
            DefaultRedisScript<Long> script = new DefaultRedisScript<>();
            script.setLocation(new ClassPathResource("lua/insertComment.lua"));
            script.setResultType(Long.class);
            String key = REDIS_COMMENT_KEY + commentAddRq.getArticleId();
            long score = c.getCreateTime().toInstant(ZoneOffset.UTC).toEpochMilli();
            Long ret = (Long) redisTemplate.execute(
                    script,
                    Arrays.asList(REDIS_COMMENT_KEY + commentAddRq.getArticleId(), REDIS_COMMENT_REPLIES_KEY + c.getId()),
                    score,      // ARGV[1]
                    JSON.toJSONString(c)        // ARGV[2]
            );
        } else {
            redisTemplate.opsForValue().increment(REDIS_COMMENT_REPLIES_KEY + c.getRootId());
        }
        return true;
    }

    @Override
    public boolean likeComment(Long id) {
        //TODO 点赞逻辑
        return true;
    }

    @Override
    public boolean deleteComment(Long id) {
        commentMapper.deleteComment(id);
        return true;
    }

    @Override
    public List<Comment> queryComment(CommentQueryRq commentQueryRq) {
        String key = REDIS_COMMENT_KEY + commentQueryRq.getArticleId();
        if (commentQueryRq.getIsPrimary() && Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            Set<Object> objSet = redisTemplate.opsForZSet()
                    .reverseRangeByScore(key,0,commentQueryRq.getCursor().toInstant(ZoneOffset.UTC).toEpochMilli()-1,0, commentQueryRq.getPageSize());
            if (objSet != null && !objSet.isEmpty()) {
                List<Comment> res = objSet.stream()
                        .map((o) -> {
                            try {
                                return objectMapper.readValue((String) o, Comment.class);
                            } catch (Exception e) {
                                log.error(e.getMessage());
                                return null;
                            }
                        }).collect(Collectors.toList());
                // 回填 replies
                res.forEach(c -> {
                    c.setReplies(Long.valueOf((Integer) Objects.requireNonNull(redisTemplate.opsForValue().get(REDIS_COMMENT_REPLIES_KEY + c.getId()))));
                });
                return res;
            }
        }
        //查询二级评论或者缓存未命中
        CommentQueryDto commentQueryDto = CommentQueryDto.builder()
                .articleId(commentQueryRq.getArticleId())
                .cursor(commentQueryRq.getCursor())
                .rootId(commentQueryRq.getRootId())
                .isPrimary(commentQueryRq.getIsPrimary())
                .offset((commentQueryRq.getPageNum() - 1) * commentQueryRq.getPageSize())
                .pageSize(commentQueryRq.getPageSize())
                .build();
        //List<Comment> ans = commentMapper.queryComment(commentQueryDto);
        if(commentQueryRq.getIsPrimary()) {
            List<Comment> ans = commentMapper.cursorQuery(commentQueryDto);
            ans.forEach((c) -> {
                        redisTemplate.opsForZSet().addIfAbsent(REDIS_COMMENT_KEY + commentQueryRq.getArticleId(), JSON.toJSONString(c), c.getCreateTime().toInstant(ZoneOffset.UTC).toEpochMilli());
                        redisTemplate.opsForValue().setIfAbsent(REDIS_COMMENT_REPLIES_KEY + c.getId(), c.getReplies(), 30, TimeUnit.MINUTES);
                    }
            );
            redisTemplate.expire(REDIS_COMMENT_KEY + commentQueryRq.getArticleId(), Duration.ofMinutes(30));
            return ans;
        }else{
            return commentMapper.queryComment(commentQueryDto);
        }
    }

    public Long countComment(Long articleId){
        return commentMapper.countComment(articleId);
    }
}
