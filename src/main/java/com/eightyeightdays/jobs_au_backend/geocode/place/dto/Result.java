package com.eightyeightdays.jobs_au_backend.geocode.place.dto;

public record Result(
        Geometry geometry,
        String formatted_address,
        String name
) {
}
