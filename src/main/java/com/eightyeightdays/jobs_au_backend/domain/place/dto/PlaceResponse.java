package com.eightyeightdays.jobs_au_backend.domain.place.dto;

import com.eightyeightdays.jobs_au_backend.domain.place.entity.Category;
import com.eightyeightdays.jobs_au_backend.domain.place.entity.Place;
import com.eightyeightdays.jobs_au_backend.domain.place.entity.Status;

import java.util.List;
import java.util.stream.Collectors;

public record PlaceResponse(
        Long id,

        String name,
        String website,
        String description,
        List<ContactResponse> contacts,

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
    public static PlaceResponse from (Place place) {

        return new PlaceResponse(
                place.getId(),
                place.getName(),
                place.getWebsite(),
                place.getDescription(),
                place.getContacts().stream()
                        .map(ContactResponse::from)
                        .toList(),

                place.getState(),
                place.getRegion(),
                place.getPostcode(),
                place.getAddress(),
                place.getLatitude(),
                place.getLongitude(),

                place.getCategory(),
                place.getSeason(),
                place.getCropType(),
                place.getPayRate(),

                place.getIsVisaExtensionEligible(),
                place.getHasAccommodation(),
                place.getStatus()
        );
    }
}
