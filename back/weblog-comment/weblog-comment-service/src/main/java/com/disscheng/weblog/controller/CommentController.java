package com.disscheng.weblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @PostMapping("/addComment")
    public void addComment(){

    }

    @PostMapping("/deleteComment")
    public void deleteComment(){

    }

    @PostMapping("/likeComment")
    public void likeComment(){

    }
}
