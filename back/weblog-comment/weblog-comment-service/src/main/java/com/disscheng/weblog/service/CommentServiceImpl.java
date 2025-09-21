package com.disscheng.weblog.service;


import com.disscheng.weblog.api.CommentService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class CommentServiceImpl implements CommentService {


    @Override
    public void HelloWorld() {
        System.out.print("Hello World!");
    }
}
