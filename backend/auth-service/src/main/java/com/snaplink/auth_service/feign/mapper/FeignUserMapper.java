package com.snaplink.auth_service.feign.mapper;

import com.snaplink.auth_service.feign.dto.UserRequestDTO;
import com.snaplink.auth_service.model.Auth;

public class FeignUserMapper {

    public static UserRequestDTO toUserRequestDTO(Auth auth) {
        return new UserRequestDTO(auth.getEmail(), auth.getAuthId());
    }
}
