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
        //查询一级评论
        CommentQueryDto commentQueryDto = CommentQueryDto.builder()
                .articleId(commentQueryRq.getArticleId())
                .rootId(commentQueryRq.getRootId())
                .isPrimary(commentQueryRq.getIsPrimary())
                .offset((commentQueryRq.getPageNum()-1)*commentQueryRq.getPageSize())
                .pageSize(commentQueryRq.getPageSize())
                .build();
        if(commentQueryRq.getIsPrimary()){
            return commentMapper.queryComment(commentQueryDto);
        }//查询二级评论
        else{
            return commentMapper.queryComment(commentQueryDto);
        }
    }
}
