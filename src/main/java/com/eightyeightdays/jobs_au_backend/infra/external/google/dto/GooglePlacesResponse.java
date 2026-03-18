package com.eightyeightdays.jobs_au_backend.infra.external.google.dto;

import com.eightyeightdays.jobs_au_backend.domain.geocode.dto.Result;

import java.util.List;

public record GooglePlacesResponse(
        List<Result> results
) {
}