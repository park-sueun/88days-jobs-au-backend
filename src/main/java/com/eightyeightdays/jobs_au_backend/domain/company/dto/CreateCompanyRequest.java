package com.eightyeightdays.jobs_au_backend.domain.company.dto;

import com.eightyeightdays.jobs_au_backend.domain.company.entity.Category;
import com.eightyeightdays.jobs_au_backend.domain.company.entity.State;
import com.eightyeightdays.jobs_au_backend.domain.company.entity.Status;

public record CreateCompanyRequest(
        String name,
        String contactNumber,
        String email,
        String website,
        String description,

        String unit,
        String street,
        String suburb,
        State state,
        String postcode,
        Double latitude,
        Double longitude,

        Category category,
        String season,
        String cropType,

        Boolean isVisaExtensionEligible,
        Status status
) {
}
