package com.snaplink.auth_service.controller;

import com.snaplink.auth_service.dto.UserRegistrationRequestDTO;
import com.snaplink.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO){
        authService.registerUser(userRegistrationRequestDTO);
        return ResponseEntity.ok("User created successfully");
    }
}
