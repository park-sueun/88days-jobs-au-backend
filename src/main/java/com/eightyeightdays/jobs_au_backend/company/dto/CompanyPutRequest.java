package com.eightyeightdays.jobs_au_backend.company.dto;

import com.eightyeightdays.jobs_au_backend.company.model.Category;
import com.eightyeightdays.jobs_au_backend.company.model.State;
import com.eightyeightdays.jobs_au_backend.company.model.Status;

public record CompanyPutRequest(
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
