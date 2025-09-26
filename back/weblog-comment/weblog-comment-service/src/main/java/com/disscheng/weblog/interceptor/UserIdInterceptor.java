package com.disscheng.weblog.interceptor;


import com.disscheng.weblog.context.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@Slf4j
public class UserIdInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (! (handler instanceof HandlerMethod)) {
            return true;
        }
        try{
            BaseContext.setUserId(Long.parseLong(request.getHeader("userId")));
            log.info("用户id"+BaseContext.getUserId()+"登录成功");
            return true;
        }catch (Exception e){
            log.error("token解析失败");
            response.setStatus(401);
            return false;
        }
    }

}
