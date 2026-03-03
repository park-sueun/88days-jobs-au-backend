package com.eightyeightdays.jobs_au_backend.auth.dto;

import com.eightyeightdays.jobs_au_backend.user.model.UserRole;

public record TokenResponse(
        String accessToken,
        String refreshToken,
        UserRole role
) {
}
