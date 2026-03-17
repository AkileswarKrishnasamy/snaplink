package com.snaplink.auth_service.feign.dto;

public record UserRequestDTO(String email, Long authId) {
}
