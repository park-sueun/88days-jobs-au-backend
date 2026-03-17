package com.eightyeightdays.jobs_au_backend.company.controller;

import com.eightyeightdays.jobs_au_backend.company.dto.CompanyCreateRequest;
import com.eightyeightdays.jobs_au_backend.company.dto.CompanyPatchRequest;
import com.eightyeightdays.jobs_au_backend.company.dto.CompanyResponse;
import com.eightyeightdays.jobs_au_backend.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
