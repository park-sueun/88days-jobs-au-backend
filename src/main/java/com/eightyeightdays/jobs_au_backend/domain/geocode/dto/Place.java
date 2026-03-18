package com.eightyeightdays.jobs_au_backend.domain.geocode.dto;

public record Place(
        String name,
        Geometry geometry,
        Address address
) {
}
