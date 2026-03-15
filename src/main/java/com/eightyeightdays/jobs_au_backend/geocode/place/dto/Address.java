package com.eightyeightdays.jobs_au_backend.geocode.place.dto;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class Address {

    private String unit;
    private String street;
    private String suburb;
    private String state;
    private String postcode;

    public static Address of(String formattedAddress) {
        Address dto = new Address();

        String[] parts = formattedAddress.split(",", 2);

        String streetPart = null;
        String[] location;

        // street part
        if (parts.length > 1) {
            streetPart = parts[0].trim();
            location = parts[1].trim().split("\\s+");
        } else {
            location = parts[0].trim().split("\\s+");
        }

        // street parsing
        if (streetPart != null) {
            if (streetPart.contains("/")) {
                String[] unitSplit = streetPart.split("/");
                dto.unit = unitSplit[0];
                dto.street = unitSplit[1];
            } else {
                dto.street = streetPart;
            }
        }

        // suburb + state + postcode
        if (location.length >= 3) {
            dto.postcode = location[location.length - 1];
            dto.state = location[location.length - 2];

            dto.suburb = String.join(
                    " ",
                    Arrays.copyOfRange(location, 0, location.length - 2)
            );
        }

        return dto;
    }
}
