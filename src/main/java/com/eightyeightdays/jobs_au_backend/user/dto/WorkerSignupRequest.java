package com.eightyeightdays.jobs_au_backend.user.dto;

import java.time.LocalDate;

public record WorkerSignupRequest(
        String nationality,
        String visaType,
        LocalDate visaExpiryDate
) {
}
