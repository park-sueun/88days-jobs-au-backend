package com.eightyeightdays.jobs_au_backend.domain.user.dto.profile;

import java.util.List;

public record EmployerProfileResponse(
        List<EmployerCompanyProfileResponse> companies
) implements ProfileResponse {

    public static EmployerProfileResponse from(List<EmployerCompanyProfileResponse> companies) {
        return new EmployerProfileResponse(
                companies
        );
    }
}
