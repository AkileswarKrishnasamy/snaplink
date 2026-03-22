package com.snaplink.auth_service.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
    private Long accessTokenExpiration;
    private Long refreshTokenExpiration;
    private String secretKey;
}
