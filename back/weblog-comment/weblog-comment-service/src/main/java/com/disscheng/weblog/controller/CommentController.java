package com.disscheng.weblog.controller;

import com.disscheng.weblog.api.CommentService;
import com.disscheng.weblog.pojo.entity.Comment;
import com.disscheng.weblog.pojo.rq.CommentAddRq;
import com.disscheng.weblog.pojo.rq.CommentQueryRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public boolean addComment(@RequestBody CommentAddRq commentAddRq){
        commentService.addComment(commentAddRq);
        return true;
    }

    @PostMapping("/deleteComment")
    public boolean deleteComment(@RequestParam Long commentId){
        commentService.deleteComment(commentId);
        return true;
    }

    @PostMapping("/likeComment")
    public boolean likeComment(@RequestParam Long commentId){
        commentService.likeComment(commentId);
        return true;
    }


    @PostMapping("/queryComment/top")
    public List<Comment> queryComment(@RequestBody CommentQueryRq commentQueryRq){
        return commentService.queryComment(commentQueryRq);
    }

}
