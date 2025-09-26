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
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@DubboService
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(CommentAddRq commentAddRq) {
        commentMapper.insertComment(
                Comment.builder()
                        .article_id(commentAddRq.getArticleId())
                        .author_id(BaseContext.getUserId())
                        .reply_id(commentAddRq.getReplyId())
                        .content(commentAddRq.getContent())
                        .isPrimary(commentAddRq.getIsPrimary())
                        .likes(0L)
                        .unlikes(0L)
                        .is_deleted(false)
                        .replies(0L)
                        .build()
        );
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
        //查询一级评论
        CommentQueryDto commentQueryDto = CommentQueryDto.builder()
                .replyId(commentQueryRq.getReplyId())
                .articleId(commentQueryRq.getArticleId())
                .isPrimary(commentQueryRq.getIsPrimary())
                .offset((commentQueryRq.getPageNum()-1)*commentQueryRq.getPageSize())
                .pageSize(commentQueryRq.getPageSize())
                .build();
        if(commentQueryRq.getIsPrimary()){
            commentMapper.queryComment(commentQueryDto);
        }//查询二级评论
        else{
            commentMapper.queryComment(commentQueryDto);
        }
        return Collections.emptyList();
    }
}
