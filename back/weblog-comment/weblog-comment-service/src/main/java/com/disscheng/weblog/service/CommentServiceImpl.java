package com.disscheng.weblog.service;


import com.disscheng.weblog.api.CommentService;
import com.disscheng.weblog.context.BaseContext;
import com.disscheng.weblog.mapper.CommentMapper;
import com.disscheng.weblog.pojo.dto.CommentQueryDto;
import com.disscheng.weblog.pojo.rq.CommentAddRq;
import com.disscheng.weblog.pojo.entity.Comment;
import com.disscheng.weblog.pojo.rq.CommentQueryRq;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@DubboService
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisTemplate<String,Comment> redisTemplate;

    private static String REDIS_COMMENT_KEY = "weblog:comment:";

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public boolean addComment(CommentAddRq commentAddRq) {
        commentMapper.insertComment(
                Comment.builder()
                        .article_id(commentAddRq.getArticleId())
                        .to_author_id(commentAddRq.getToAuthorId())
                        .author_id(BaseContext.getUserId())
                        .reply_id(commentAddRq.getReplyId())
                        .root_id(commentAddRq.getRootId())
                        .content(commentAddRq.getContent())
                        .is_primary(commentAddRq.getIsPrimary())
                        .likes(0L)
                        .is_deleted(false)
                        .replies(0L)
                        .build()
        );
        Comment rootComment = commentMapper.selectComment(commentAddRq.getRootId());
        if(rootComment!=null){
            commentMapper.updateComment(
                    Comment.builder()
                            .id(commentAddRq.getRootId())
                            .replies(rootComment.getReplies()+1)
                            .build()
            );
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
        //查询一级评论先从缓存中取
        if(commentQueryRq.getIsPrimary()&&Boolean.TRUE.equals(redisTemplate.hasKey(REDIS_COMMENT_KEY + commentQueryRq.getArticleId()))){
            return new ArrayList<>(Objects.requireNonNull(redisTemplate.opsForZSet()
                    .range(REDIS_COMMENT_KEY + commentQueryRq.getArticleId(), (long) (commentQueryRq.getPageNum() - 1) * commentQueryRq.getPageSize(), ((long) commentQueryRq.getPageNum() * commentQueryRq.getPageSize()))));
        }

        //查询二级评论或者缓存未命中
        CommentQueryDto commentQueryDto = CommentQueryDto.builder()
                .articleId(commentQueryRq.getArticleId())
                .rootId(commentQueryRq.getRootId())
                .isPrimary(commentQueryRq.getIsPrimary())
                .offset((commentQueryRq.getPageNum()-1)*commentQueryRq.getPageSize())
                .pageSize(commentQueryRq.getPageSize())
                .build();
        List<Comment> ans = commentMapper.queryComment(commentQueryDto);
        ans.forEach((c)->
                redisTemplate.opsForZSet().addIfAbsent(REDIS_COMMENT_KEY + commentQueryRq.getArticleId(),c,c.getCreateTime().toInstant(ZoneOffset.UTC).toEpochMilli())
        );
        redisTemplate.expire(REDIS_COMMENT_KEY + commentQueryRq.getArticleId(), Duration.ofMinutes(30));
        return commentMapper.queryComment(commentQueryDto);
    }
}
