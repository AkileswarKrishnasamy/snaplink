package com.snaplink.user_service.controller;

import com.snaplink.user_service.dto.UserRequestDTO;
import com.snaplink.user_service.dto.UserResponseDTO;
import com.snaplink.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    private ResponseEntity<UserResponseDTO> saveUser(@Valid UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.saveUser(userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

}
