package com.snaplink.auth_service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UserLoginRequestDTO(@Email(message = "Enter a valid email") String email,
                                  @NotBlank(message = "Password cannot be blank") String password) {
}
