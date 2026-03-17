package com.snaplink.auth_service.mapper;

import com.snaplink.auth_service.dto.UserRegistrationRequestDTO;
import com.snaplink.auth_service.model.Auth;

public class AuthMapper {

    public static Auth toEntity(UserRegistrationRequestDTO userRegistrationRequestDTO){
        Auth auth = new Auth();
        auth.setEmail(userRegistrationRequestDTO.email());
        auth.setPassword(userRegistrationRequestDTO.password());
        return auth;
    }

}
