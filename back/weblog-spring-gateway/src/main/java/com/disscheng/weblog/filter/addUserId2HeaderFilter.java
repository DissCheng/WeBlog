package com.disscheng.weblog.filter;

import com.disscheng.weblog.utils.jwt.JwtProperties;
import com.disscheng.weblog.utils.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class addUserId2HeaderFilter implements GlobalFilter {

    private static final String HEADER_USER_ID = "userId";

    @Autowired
    JwtProperties jwtProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("==================> TokenConvertFilter");
        // 用户 ID
        Long userId = null;
        try {
            String token = exchange.getRequest().getHeaders().getFirst(jwtProperties.getTokenName());
            // 获取当前登录用户的 ID
            userId = Long.parseLong(JwtUtil.parseJwt(token,jwtProperties.getSecretKey()).get("userId").toString());
        } catch (Exception e) {
            // 若没有登录，则直接放行
            return chain.filter(exchange);
        }
        log.info("## 当前登录的用户 ID: {}", userId);
        Long finalUserId = userId;
        ServerWebExchange newExchange = exchange.mutate()
                .request(builder -> builder.header(HEADER_USER_ID, String.valueOf(finalUserId))) // 将用户 ID 设置到请求头中
                .build();
        return chain.filter(newExchange);
    }

}
