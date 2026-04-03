package com.eightyeightdays.jobs_au_backend.domain.place.dto;

public record ContactRequest(
        String name,
        String phone,
        String email
) {
}
