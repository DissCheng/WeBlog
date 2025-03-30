package com.disscheng.weblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@EnableCaching
public class WeblogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeblogServerApplication.class, args);
    }

}
