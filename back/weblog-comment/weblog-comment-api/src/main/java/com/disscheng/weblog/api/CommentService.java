package com.disscheng.weblog.api;


import com.disscheng.weblog.pojo.dto.CommentRq;
import com.disscheng.weblog.pojo.entity.Comment;

import java.util.List;

public interface CommentService {
    public void HelloWorld();

    public boolean addComment(CommentRq commentRq);

    public boolean updateComment(CommentRq commentRq);

    public boolean deleteComment(Long id);

    public List<Comment> queryComment(Long articleId);
}
