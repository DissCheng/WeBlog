package com.disscheng.weblog.config;


import com.disscheng.weblog.context.BaseContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfiguration {

    @Bean
    public BaseContext baseContext() {
        return new BaseContext();
    }
}
