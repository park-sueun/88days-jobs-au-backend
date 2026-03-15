package com.eightyeightdays.jobs_au_backend.geocode.place.dto;

import lombok.Getter;

@Getter
public class Address {

    private String unit;
    private String street;
    private String suburb;
    private String state;
    private String postcode;

    public static Address of(String formattedAddress) {
        Address dto = new Address();

        String[] parts = formattedAddress.split(",");

        // street part
        String streetPart = parts[0].trim();

        if (streetPart.contains("/")) {
            String[] unitSplit = streetPart.split("/");
            dto.unit = unitSplit[0];
            dto.street = unitSplit[1];
        } else {
            dto.street = streetPart;
        }

        // suburb + state + postcode
        String[] location = parts[1].trim().split(" ");

        dto.suburb = location[0];
        dto.state = location[1];
        dto.postcode = location[2];

        return dto;
    }
}
