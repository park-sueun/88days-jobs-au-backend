package com.eightyeightdays.jobs_au_backend.domain.company.admin;

import com.eightyeightdays.jobs_au_backend.domain.company.dto.CreateCompanyRequest;
import com.eightyeightdays.jobs_au_backend.domain.company.dto.UpdateCompanyRequest;
import com.eightyeightdays.jobs_au_backend.domain.company.dto.CompanyResponse;
import com.eightyeightdays.jobs_au_backend.domain.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/companies")
@RequiredArgsConstructor
public class AdminCompanyController {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateCompanyRequest request) {

        companyService.create(request);
    }

    @PutMapping("/{id}")
    public CompanyResponse put(@PathVariable Long id, @RequestBody UpdateCompanyRequest request) {

        return companyService.put(id, request);
    }

    @PatchMapping("/{id}")
    public CompanyResponse patch(@PathVariable Long id, @RequestBody UpdateCompanyRequest request) {

        return companyService.patch(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        companyService.delete(id);
    }
}
