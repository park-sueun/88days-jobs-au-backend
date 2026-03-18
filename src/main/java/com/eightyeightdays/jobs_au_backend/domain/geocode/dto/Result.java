package com.eightyeightdays.jobs_au_backend.domain.geocode.dto;

public record Result(
        Geometry geometry,
        String formatted_address,
        String name
) {
}
