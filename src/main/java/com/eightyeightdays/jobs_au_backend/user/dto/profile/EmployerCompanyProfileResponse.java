package com.eightyeightdays.jobs_au_backend.user.dto.profile;

import com.eightyeightdays.jobs_au_backend.company.model.Company;
import com.eightyeightdays.jobs_au_backend.user.model.EmployerCompanyProfile;

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
