package com.eightyeightdays.jobs_au_backend.domain.company.dto;

import com.eightyeightdays.jobs_au_backend.domain.company.entity.Category;
import com.eightyeightdays.jobs_au_backend.domain.company.entity.Company;
import com.eightyeightdays.jobs_au_backend.domain.company.entity.State;
import com.eightyeightdays.jobs_au_backend.domain.company.entity.Status;

public record CompanyResponse(
        Long id,

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

        boolean isVisaExtensionEligible,
        Status status
) {
    public static CompanyResponse from(Company company) {

        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getContactNumber(),
                company.getEmail(),
                company.getWebsite(),
                company.getDescription(),

                company.getUnit(),
                company.getStreet(),
                company.getSuburb(),
                company.getState(),
                company.getPostcode(),
                company.getLatitude(),
                company.getLongitude(),

                company.getCategory(),
                company.getSeason(),
                company.getCropType(),

                company.getIsVisaExtensionEligible(),
                company.getStatus()
        );

    }
}
