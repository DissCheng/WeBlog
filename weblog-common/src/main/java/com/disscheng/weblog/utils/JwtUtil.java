package com.disscheng.weblog.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    /**
     * 生成token
     * @param secretKey 密钥
     * @param ttlMills 有效期
     * @param claims 自定义信息
     * @return token
     */
    public static String createJwt(String secretKey, long ttlMills, Map<String, Object> claims){
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + ttlMills;
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    /**
     * 解析token
     * @param token token
     * @param secretKey 密钥
     * @return 自定义信息
     */
    public static Claims parseJwt(String token, String secretKey){

        return Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
