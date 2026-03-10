package com.snaplink.user_service.service;

import com.snaplink.user_service.dto.UserRequestDTO;
import com.snaplink.user_service.dto.UserResponseDTO;
import com.snaplink.user_service.mapper.UserMapper;
import com.snaplink.user_service.model.User;
import com.snaplink.user_service.repository.UserRepository;
import com.snaplink.user_service.utils.UsernameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    private final UsernameGenerator usernameGenerator;

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        User user = UserMapper.toEntity(userRequestDTO);
        String uniqueUsername = usernameGenerator.generateWithEmail(user.getUsername()); // Initially mapped with Email
        user.setUsername(uniqueUsername);
        User savedUser = userRepo.save(user);
        return UserMapper.toDto(savedUser);
    }

    public Long getUserId(Long authId){
        User user = userRepo.findByAuthId(authId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUserId();
    }

    public UserResponseDTO findUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not Found"));
        return UserMapper.toDto(user);
    }

}
