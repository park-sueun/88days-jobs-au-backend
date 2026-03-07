package com.eightyeightdays.jobs_au_backend.company.controller;

import com.eightyeightdays.jobs_au_backend.company.dto.CompanyCreateRequest;
import com.eightyeightdays.jobs_au_backend.company.dto.CompanyPatchRequest;
import com.eightyeightdays.jobs_au_backend.company.dto.CompanyResponse;
import com.eightyeightdays.jobs_au_backend.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CompanyCreateRequest request) {

        companyService.create(request);

    }

    @GetMapping
    public List<CompanyResponse> getList() {

        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyResponse get(@PathVariable Long id) {

        return companyService.get(id);
    }

    @PatchMapping("/{id}")
    public CompanyResponse patch(@PathVariable Long id, @RequestBody CompanyPatchRequest request) {

        return companyService.patch(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        companyService.delete(id);
    }

}
