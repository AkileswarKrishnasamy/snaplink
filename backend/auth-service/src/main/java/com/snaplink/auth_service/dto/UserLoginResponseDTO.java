package com.snaplink.auth_service.dto;

public record UserLoginResponseDTO(String accessToken, String tokenType, long expiresIn) {
}
