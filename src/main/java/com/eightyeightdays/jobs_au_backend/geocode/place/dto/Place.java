package com.eightyeightdays.jobs_au_backend.geocode.place.dto;

public record Place(
        String name,
        Geometry geometry,
        Address address
) {
}
