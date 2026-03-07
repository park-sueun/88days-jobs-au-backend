package com.eightyeightdays.jobs_au_backend.company.dto;

import com.eightyeightdays.jobs_au_backend.company.model.PayType;
import com.eightyeightdays.jobs_au_backend.company.model.State;
import com.eightyeightdays.jobs_au_backend.company.model.Status;
import com.eightyeightdays.jobs_au_backend.company.model.WorkType;
import lombok.Getter;

public record CompanyPatchRequest(
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

        Boolean isVisaExtensionEligible,
        Status status
) {
}
