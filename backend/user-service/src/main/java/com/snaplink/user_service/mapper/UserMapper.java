package com.snaplink.user_service.mapper;

import com.snaplink.user_service.dto.UserRequestDTO;
import com.snaplink.user_service.dto.UserResponseDTO;
import com.snaplink.user_service.model.User;

public class UserMapper {

    public static UserResponseDTO toDto(User user){
        return new UserResponseDTO(user.getUsername(), user.getAuthId());
    }

    public static User toEntity(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setUsername(userRequestDTO.email());
        user.setAuthId(userRequestDTO.authId());
        return user;
    }
}
