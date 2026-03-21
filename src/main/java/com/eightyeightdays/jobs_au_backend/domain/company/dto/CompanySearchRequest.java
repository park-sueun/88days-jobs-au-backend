package com.eightyeightdays.jobs_au_backend.domain.company.dto;

import com.eightyeightdays.jobs_au_backend.domain.company.entity.Category;
import com.eightyeightdays.jobs_au_backend.domain.company.entity.State;

public record CompanySearchRequest (
    State state,
    String season,
    String cropType,
    Category category
) {}