package com.disscheng.weblog.api;


import com.disscheng.weblog.pojo.rq.CommentQueryRq;
import com.disscheng.weblog.pojo.rq.CommentAddRq;
import com.disscheng.weblog.pojo.entity.Comment;
import java.util.List;

public interface CommentService {
    public boolean addComment(CommentAddRq commentAddRq);

    public boolean deleteComment(Long id);

    public List<Comment> queryComment(CommentQueryRq commentQueryRq);

    public boolean likeComment(Long id);
}
