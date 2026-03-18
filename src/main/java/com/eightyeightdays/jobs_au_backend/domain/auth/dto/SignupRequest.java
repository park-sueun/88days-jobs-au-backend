package com.eightyeightdays.jobs_au_backend.domain.auth.dto;

import com.eightyeightdays.jobs_au_backend.domain.user.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SignupRequest(
        @Email @NotNull String email,
        @NotNull String password,
        @NotNull String firstName,
        @NotNull String lastName,
        String phone,
        String profileImageUrl,
        @NotNull UserRole role
) {
}
