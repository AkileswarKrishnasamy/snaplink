package com.snaplink.auth_service.service;

import com.snaplink.auth_service.config.JwtProperties;
import com.snaplink.auth_service.enums.TokenType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    /**
     * Generates the JWT token with the claims and based on the Type
     *
     * @param claims map which contains the claim string and the claim object.
     * @param type   type of the token (E.g. ACCESS, REFRESH)
     * @return a compact, JWT token
     */
    public String generateToken(Map<String, Object> claims, TokenType type) {
        long expirationTime = resolveExpiration(type);
        Key signingKey = resolveKey(jwtProperties.getSecretKey());
        return buildToken(claims, expirationTime, signingKey);
    }

    /**
     * Gets the Expiration time for the Token Type
     *
     * @param type Type of the token (E.g. ACCESS, REFRESH).
     * @return Expiration time in milliseconds.
     */
    private long resolveExpiration(TokenType type) {
        return switch (type) {
            case ACCESS -> jwtProperties.getAccessTokenExpiration();
            case REFRESH -> jwtProperties.getRefreshTokenExpiration();
        };
    }

    /**
     * Builds a signing key from base64 encoded string.
     *
     * @param secretKey secret key which is base64 encoded
     * @return a HMAC Signing key
     */
    private Key resolveKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Builds JWT Token with the given expiration time and claims
     *
     * @param claims     custom claims to be embedded in the payload.
     * @param expiration expiration time for the token in milliseconds.
     * @param signingKey secret key to sign the token
     * @return a compact, jwt token
     */
    private String buildToken(Map<String, Object> claims, Long expiration, Key signingKey) {
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(signingKey)
                .compact();
    }
}
