package com.eightyeightdays.jobs_au_backend.company.dto;

import com.eightyeightdays.jobs_au_backend.company.model.*;

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

        WorkType workType,
        PayType payType,
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

                company.getWorkType(),
                company.getPayType(),
                company.getCropType(),

                company.getIsVisaExtensionEligible(),
                company.getStatus()
        );

    }
}
