package com.eightyeightdays.jobs_au_backend.domain.job.entity;

public enum PayType {
    HOURLY,
    PIECE_RATE,
    SALARY,
    HOURLY_AND_PIECE_RATE, // TODO: Delete
    FLEXIBLE_PAY

    // TODO: Jobs로 이동, Company에서는 삭제
}
