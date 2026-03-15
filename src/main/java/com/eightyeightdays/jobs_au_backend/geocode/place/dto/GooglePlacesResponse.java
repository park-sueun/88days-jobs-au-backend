package com.eightyeightdays.jobs_au_backend.geocode.place.dto;

import java.util.List;

public record GooglePlacesResponse(
        List<Result> results
) {
}