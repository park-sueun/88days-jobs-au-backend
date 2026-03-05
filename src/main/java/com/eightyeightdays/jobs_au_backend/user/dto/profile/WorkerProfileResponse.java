package com.eightyeightdays.jobs_au_backend.user.dto.profile;

import com.eightyeightdays.jobs_au_backend.user.model.VisaType;
import com.eightyeightdays.jobs_au_backend.user.model.WorkerProfile;

import java.time.LocalDate;

public record WorkerProfileResponse(
        Long id,
        String nationality,
        VisaType visaType,
        LocalDate visaExpiryDate
) implements ProfileResponse {

    public static WorkerProfileResponse from(WorkerProfile worker) {

        return new WorkerProfileResponse(
                worker.getId(),
                worker.getNationality(),
                worker.getVisaType(),
                worker.getVisaExpiryDate()
        );
    }
}
