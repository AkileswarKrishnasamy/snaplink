package com.snaplink.user_service.controller;

import com.snaplink.user_service.dto.UserRequestDTO;
import com.snaplink.user_service.dto.UserResponseDTO;
import com.snaplink.user_service.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    private ResponseEntity<UserResponseDTO> findUser(@PathVariable  @NotNull(message = "User Id cannot be null") Long userId){
        UserResponseDTO userResponseDTO = userService.findUser(userId);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PostMapping("/save")
    private ResponseEntity<UserResponseDTO> saveUser(@Valid UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.saveUser(userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

}
