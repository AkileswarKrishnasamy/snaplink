package com.snaplink.auth_service.service;

import com.snaplink.auth_service.dto.UserRegistrationRequestDTO;
import com.snaplink.auth_service.mapper.AuthMapper;
import com.snaplink.auth_service.model.Auth;
import com.snaplink.auth_service.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserRegistrationRequestDTO userRegistrationRequestDTO){
        Auth auth = AuthMapper.toEntity(userRegistrationRequestDTO);
        auth.setPassword(passwordEncoder.encode(auth.getPassword()));
        authRepository.save(auth);
    }
}
