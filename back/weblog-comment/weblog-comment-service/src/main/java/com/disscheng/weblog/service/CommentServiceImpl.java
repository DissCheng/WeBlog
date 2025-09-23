package com.disscheng.weblog.service;


import com.disscheng.weblog.api.CommentService;
import com.disscheng.weblog.mapper.CommentMapper;
import com.disscheng.weblog.pojo.dto.CommentRq;
import com.disscheng.weblog.pojo.entity.Comment;
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
    public void HelloWorld() {
        System.out.print("Hello World!");
    }

    @Override
    public boolean addComment(CommentRq commentRq) {
        commentMapper.insertComment(
                Comment.builder()
                        .article_id(commentRq.getArticle_id())
                        .author_id(commentRq.getAuthor_id())
                        .reply_id(commentRq.getReply_id())
                        .content(commentRq.getContent())
                        .isPrimary(commentRq.getIsPrimary())
                        .likes(0L)
                        .build()
        );
        return true;
    }

    @Override
    public boolean updateComment(CommentRq commentRq) {
        return true;
    }

    @Override
    public boolean deleteComment(Long id) {
        return true;
    }

    @Override
    public List<Comment> queryComment(Long articleId) {
        return Collections.emptyList();
    }
}
