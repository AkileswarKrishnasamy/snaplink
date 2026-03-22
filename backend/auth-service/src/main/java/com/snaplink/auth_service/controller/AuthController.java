package com.snaplink.auth_service.controller;

import com.snaplink.auth_service.config.JwtProperties;
import com.snaplink.auth_service.dto.UserLoginRequestDTO;
import com.snaplink.auth_service.dto.UserRegistrationRequestDTO;
import com.snaplink.auth_service.service.AuthService;
import com.snaplink.auth_service.service.JwtService;
import com.snaplink.auth_service.utils.TokenPair;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtProperties jwtProperties;

    @Value("#{'${spring.profiles.active:dev}' == 'dev'}")
    private boolean isDevelopment;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO){
        authService.registerUser(userRegistrationRequestDTO);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String userAgent = httpServletRequest.getHeader("User-Agent");
        String ipAddr = httpServletRequest.getRemoteAddr();

        TokenPair tokenPair = authService.loginUser(userLoginRequestDTO, userAgent, ipAddr);

        Cookie refreshCookie = new Cookie("refresh-token", tokenPair.refreshToken());
        refreshCookie.setHttpOnly(true);
        refreshCookie.setPath("/auth/refresh");
        refreshCookie.setSecure(!isDevelopment);
        refreshCookie.setMaxAge((int) (jwtProperties.getRefreshTokenExpiration() / 1000));
        httpServletResponse.addCookie(refreshCookie);

        return ResponseEntity.ok(tokenPair.accessToken());
    }
}
