package com.eightyeightdays.jobs_au_backend.domain.auth.dto;

import com.eightyeightdays.jobs_au_backend.domain.user.entity.UserRole;

public record TokenResponse(
        String accessToken,
        String refreshToken,
        UserRole role
) {
}
