package com.disscheng.weblog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeblogWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeblogWebApplication.class, args);
        System.out.println("Weblog Web started successfully!");
    }
}
