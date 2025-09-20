package com.disscheng.weblog.interceptor;


import com.disscheng.weblog.context.BaseContext;
import com.disscheng.weblog.properties.JwtProperties;
import com.disscheng.weblog.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (! (handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader(jwtProperties.getTokenName());
        try{
            Claims claims = JwtUtil.parseJwt(token, jwtProperties.getSecretKey());
            BaseContext.setUserId(Long.parseLong(claims.get("userId").toString()));
            log.info("用户id"+BaseContext.getUserId()+"登录成功");
            return true;
        }catch (Exception e){
            log.error("token解析失败");
            response.setStatus(401);
            return false;
        }
    }

}
