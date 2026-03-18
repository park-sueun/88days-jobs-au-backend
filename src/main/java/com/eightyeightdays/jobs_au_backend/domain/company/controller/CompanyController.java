package com.eightyeightdays.jobs_au_backend.domain.company.controller;

import com.eightyeightdays.jobs_au_backend.domain.company.dto.CompanyResponse;
import com.eightyeightdays.jobs_au_backend.domain.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public Page<CompanyResponse> getCompanies(Pageable pageable) {

        return companyService.getCompanies(pageable);
    }

    @GetMapping("/{id}")
    public CompanyResponse get(@PathVariable Long id) {

        return companyService.get(id);
    }

}
