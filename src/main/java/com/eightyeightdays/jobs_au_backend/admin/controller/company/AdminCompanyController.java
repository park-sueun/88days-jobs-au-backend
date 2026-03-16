package com.eightyeightdays.jobs_au_backend.admin.controller.company;

import com.eightyeightdays.jobs_au_backend.company.dto.CompanyCreateRequest;
import com.eightyeightdays.jobs_au_backend.company.dto.CompanyPatchRequest;
import com.eightyeightdays.jobs_au_backend.company.dto.CompanyPutRequest;
import com.eightyeightdays.jobs_au_backend.company.dto.CompanyResponse;
import com.eightyeightdays.jobs_au_backend.company.service.CompanyService;
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
    public void create(@RequestBody CompanyCreateRequest request) {

        companyService.create(request);
    }

    @PutMapping("/{id}")
    public CompanyResponse put(@PathVariable Long id, @RequestBody CompanyPutRequest request) {

        return companyService.put(id, request);
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
