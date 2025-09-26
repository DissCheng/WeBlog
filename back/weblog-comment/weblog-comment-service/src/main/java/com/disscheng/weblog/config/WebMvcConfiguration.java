package com.disscheng.weblog.config;

import com.disscheng.weblog.interceptor.UserIdInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private UserIdInterceptor userIdInterceptor;

    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("拦截器注册成功");
        registry.addInterceptor(userIdInterceptor).addPathPatterns("/**");

    }
}
