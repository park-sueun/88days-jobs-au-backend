package com.eightyeightdays.jobs_au_backend.domain.place.dto;

import com.eightyeightdays.jobs_au_backend.domain.place.entity.Category;
import com.eightyeightdays.jobs_au_backend.domain.place.entity.Status;

public record PlaceRequest(
        String name,
        String website,
        String description,
        List<ContactRequest> contacts,

        String state,
        String region,
        String postcode,
        String address,
        Double latitude,
        Double longitude,

        Category category,
        String season,
        String cropType,
        String payRate,

        Boolean isVisaExtensionEligible,
        Boolean hasAccommodation,

        Status status
) {
}
