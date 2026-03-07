package com.eightyeightdays.jobs_au_backend.company.service;

import com.eightyeightdays.jobs_au_backend.company.dto.CompanyCreateRequest;
import com.eightyeightdays.jobs_au_backend.company.dto.CompanyPatchRequest;
import com.eightyeightdays.jobs_au_backend.company.dto.CompanyResponse;
import com.eightyeightdays.jobs_au_backend.company.model.Company;
import com.eightyeightdays.jobs_au_backend.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public void create(CompanyCreateRequest request) {
        
        Company company = Company.builder()
                .name(request.name())
                .contactNumber(request.contactNumber())
                .email(request.email())
                .website(request.website())
                .description(request.description())

                .unit(request.unit())
                .street(request.street())
                .suburb(request.suburb())
                .state(request.state())
                .postcode(request.postcode())

                .latitude(request.latitude())
                .longitude(request.longitude())

                .workType(request.workType())
                .payType(request.payType())
                .cropType(request.cropType())

                .isVisaExtensionEligible(request.isVisaExtensionEligible())
                .status(request.status())

                .build();
                
        companyRepository.save(company);
    }

    public List<CompanyResponse> findAll() {

        List<Company> companies = companyRepository.findAll();

        return companies.stream()
                .map(company -> CompanyResponse.from(company))
                .toList();
    }

    public CompanyResponse get(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Company not found"));

        return CompanyResponse.from(company);
    }

    public CompanyResponse patch(Long id, CompanyPatchRequest request) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Company not found"));

        company.patch(request);

        return CompanyResponse.from(company);
    }

    public void delete(Long id) {

        Company company = companyRepository.findById(id)
                        .orElseThrow(() -> new IllegalStateException("Company not found"));

        companyRepository.delete(company);

        companyRepository.deleteById(id);
    }
}
