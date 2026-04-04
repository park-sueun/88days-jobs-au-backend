package com.eightyeightdays.jobs_au_backend.domain.place.dto;

import java.util.List;

public record PlacesResponse(
        List<PlaceResponse> places,
        int count
) {
}
