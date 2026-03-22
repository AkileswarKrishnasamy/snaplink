package com.snaplink.auth_service.service;

import com.snaplink.auth_service.config.JwtProperties;
import com.snaplink.auth_service.model.Auth;
import com.snaplink.auth_service.model.AuthSession;
import com.snaplink.auth_service.repository.AuthSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthSessionService {

    private final AuthSessionRepository authSessionRepository;
    private final JwtProperties jwtProperties;

    public AuthSession createSession(Auth auth, String userAgent, String ipAddr){
        LocalDateTime now = LocalDateTime.now();
        AuthSession session = new AuthSession();
        session.setAuth(auth);
        session.setUserAgent(userAgent);
        session.setIpAddress(ipAddr);
        session.setIsRevoked(false);
        session.setLastUsedAt(now);
        session.setExpiresAt(now.plus(Duration.ofMillis(jwtProperties.getRefreshTokenExpiration())));
        return authSessionRepository.save(session);
    }

    public AuthSession updateToken(AuthSession authSession, String token){
        authSession.setToken(token);
        return authSessionRepository.save(authSession);
    }

}
