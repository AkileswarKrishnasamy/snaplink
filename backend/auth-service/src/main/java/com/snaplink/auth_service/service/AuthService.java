package com.snaplink.auth_service.service;

import com.snaplink.auth_service.dto.UserRegistrationRequestDTO;
import com.snaplink.auth_service.feign.UserClient;
import com.snaplink.auth_service.feign.dto.UserRequestDTO;
import com.snaplink.auth_service.feign.mapper.FeignUserMapper;
import com.snaplink.auth_service.mapper.AuthMapper;
import com.snaplink.auth_service.model.Auth;
import com.snaplink.auth_service.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserClient userClient;

    public void registerUser(UserRegistrationRequestDTO userRegistrationRequestDTO){
        Auth auth = AuthMapper.toEntity(userRegistrationRequestDTO);
        auth.setPassword(passwordEncoder.encode(auth.getPassword()));
        Auth authResponse = authRepository.save(auth);
        log.info("authId: {}", authResponse.getAuthId());
        UserRequestDTO userRequestDTO = FeignUserMapper.toUserRequestDTO(authResponse);
        userClient.saveUser(userRequestDTO);
    }
}
