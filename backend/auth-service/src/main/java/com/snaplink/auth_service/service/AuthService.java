package com.snaplink.auth_service.service;

import com.snaplink.auth_service.dto.UserLoginRequestDTO;
import com.snaplink.auth_service.dto.UserRegistrationRequestDTO;
import com.snaplink.auth_service.enums.TokenType;
import com.snaplink.auth_service.feign.UserClient;
import com.snaplink.auth_service.feign.dto.UserRequestDTO;
import com.snaplink.auth_service.feign.mapper.FeignUserMapper;
import com.snaplink.auth_service.mapper.AuthMapper;
import com.snaplink.auth_service.model.Auth;
import com.snaplink.auth_service.model.AuthSession;
import com.snaplink.auth_service.repository.AuthRepository;
import com.snaplink.auth_service.utils.TokenPair;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserClient userClient;
    private final JwtService jwtService;
    private final AuthSessionService authSessionService;

    public void registerUser(UserRegistrationRequestDTO userRegistrationRequestDTO){
        Auth auth = AuthMapper.toEntity(userRegistrationRequestDTO);
        auth.setPassword(passwordEncoder.encode(auth.getPassword()));
        Auth authResponse = authRepository.save(auth);
        log.info("authId: {}", authResponse.getAuthId());
        UserRequestDTO userRequestDTO = FeignUserMapper.toUserRequestDTO(authResponse);
        userClient.saveUser(userRequestDTO);
    }

    public TokenPair loginUser(UserLoginRequestDTO userLoginRequestDTO, String userAgent, String ipAddr){
        Auth auth = authRepository.findByEmail(userLoginRequestDTO.email())
                .orElseThrow(() ->  new RuntimeException("Invalid Credentials"));
        boolean result = passwordEncoder.matches(userLoginRequestDTO.password(), auth.getPassword());
        if (!result){
            throw new RuntimeException("Invalid Credentials");
        }
        Long userId = userClient.findUserWithAuth(auth.getAuthId());

        Map<String, Object> accessTokenClaims = HashMap.newHashMap(2);
        accessTokenClaims.put("uid", userId);
        accessTokenClaims.put("aid", auth.getAuthId());

        AuthSession session = authSessionService.createSession(auth, userAgent, ipAddr);
        Map<String, Object> refreshTokenClaims = HashMap.newHashMap(1);
        refreshTokenClaims.put("sid", session.getAuthSessionId());

        String accessToken = jwtService.generateToken(accessTokenClaims, TokenType.ACCESS);
        String refreshToken = jwtService.generateToken(refreshTokenClaims, TokenType.REFRESH);

        authSessionService.updateToken(session, refreshToken);
        return new TokenPair(accessToken, refreshToken);
    }
}
