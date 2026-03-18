package com.eightyeightdays.jobs_au_backend.domain.user.dto.profile;

import com.eightyeightdays.jobs_au_backend.domain.user.entity.EmployerCompanyProfile;

public record EmployerCompanyProfileResponse(
        Long id,
        Long companyId
) implements ProfileResponse {

    public static EmployerCompanyProfileResponse from(EmployerCompanyProfile profile) {

        return new EmployerCompanyProfileResponse(
                profile.getId(),
                profile.getCompany().getId()
        );
    }
}
