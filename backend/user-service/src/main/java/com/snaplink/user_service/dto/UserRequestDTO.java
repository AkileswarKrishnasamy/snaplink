package com.snaplink.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(@Email(message = "Email is required") String email,
                             @NotNull(message = "Auth Id cannot be null") Long authId) {
}
