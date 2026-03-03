package com.eightyeightdays.jobs_au_backend.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
