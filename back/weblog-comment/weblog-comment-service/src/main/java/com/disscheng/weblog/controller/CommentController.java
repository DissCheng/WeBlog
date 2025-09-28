package com.disscheng.weblog.controller;

import com.disscheng.weblog.api.CommentService;
import com.disscheng.weblog.pojo.Response;
import com.disscheng.weblog.pojo.rq.CommentAddRq;
import com.disscheng.weblog.pojo.rq.CommentQueryRq;
import com.disscheng.weblog.pojo.vo.CommentQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.google.common.util.concurrent.RateLimiter;

@RestController
@RequestMapping("/article/comment/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Value()
    private RateLimiter rateLimiter = RateLimiter.create();

    @PostMapping("/addComment")
    public Response<Boolean> addComment(@RequestBody CommentAddRq commentAddRq){
        commentService.addComment(commentAddRq);
        return Response.<Boolean>builder()
                .code(200)
                .msg("success")
                .data(true)
                .build();
    }

    @PostMapping("/deleteComment")
    public Response<Boolean> deleteComment(@RequestParam Long commentId){
        commentService.deleteComment(commentId);
        return Response.<Boolean>builder()
                .code(200)
                .msg("success")
                .data(true)
                .build();
    }

    @PostMapping("/likeComment")
    public Response<Boolean> likeComment(@RequestParam Long commentId){
        commentService.likeComment(commentId);
        return Response.<Boolean>builder()
                .code(200)
                .msg("success")
                .data(true)
                .build();
    }


    @GetMapping("/queryComment")
    public Response<CommentQueryVo> queryComment(CommentQueryRq commentQueryRq){
        CommentQueryVo commentQueryVo = CommentQueryVo.builder()
                .comment(commentService.queryComment(commentQueryRq))
                .build();
        return Response.<CommentQueryVo>builder()
                .code(200)
                .msg("success")
                .data(commentQueryVo)
                .build();
    }

}
