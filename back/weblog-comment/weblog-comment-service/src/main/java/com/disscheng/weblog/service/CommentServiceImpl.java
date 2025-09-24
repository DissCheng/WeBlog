package com.disscheng.weblog.service;


import com.disscheng.weblog.api.CommentService;
import com.disscheng.weblog.mapper.CommentMapper;
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
    public void HelloWorld() {
        System.out.print("Hello World!");
    }



    @Override
    public boolean addComment(CommentAddRq commentAddRq) {
        commentMapper.insertComment(
                Comment.builder()
                        .article_id(commentAddRq.getArticle_id())
                        .author_id(commentAddRq.getAuthor_id())
                        .reply_id(commentAddRq.getReply_id())
                        .content(commentAddRq.getContent())
                        .isPrimary(commentAddRq.getIsPrimary())
                        .likes(0L)
                        .build()
        );
        return true;
    }

    @Override
    public boolean likeComment(Long id) {

        return true;
    }

    @Override
    public boolean deleteComment(Long id) {
        commentMapper.deleteComment(id);
        return true;
    }

    @Override
    public List<Comment> queryComment(CommentQueryRq commentQueryRq) {
        return Collections.emptyList();
    }
}
