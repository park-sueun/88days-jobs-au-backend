package com.eightyeightdays.jobs_au_backend.domain.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
