package com.disscheng.weblog.utils.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weblog.jwt")
@Data
public class JwtProperties {
    private String secretKey;
    private long Ttl;
    private String tokenName;
}
