package com.eightyeightdays.jobs_au_backend.company.service;

import com.eightyeightdays.jobs_au_backend.company.dto.CompanyCreateRequest;
import com.eightyeightdays.jobs_au_backend.company.dto.CompanyPatchRequest;
import com.eightyeightdays.jobs_au_backend.company.dto.CompanyPutRequest;
import com.eightyeightdays.jobs_au_backend.company.dto.CompanyResponse;
import com.eightyeightdays.jobs_au_backend.company.model.Company;
import com.eightyeightdays.jobs_au_backend.company.repository.CompanyRepository;
import com.eightyeightdays.jobs_au_backend.geocode.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final PlaceService placeService;

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

                .category(request.category())
                .season(request.season())
                .cropType(request.cropType())

                .isVisaExtensionEligible(request.isVisaExtensionEligible())
                .status(request.status())

                .build();

//        placeService.findByCompany(company)
//                        .ifPresent(place -> {
//                                    company.updateLocation(place.geometry().location());
//                                    company.updateAddress(place.address());
//                                });

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

    @Transactional
    public CompanyResponse put(Long id, CompanyPutRequest request) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Company not found"));

        company.update(request);

        return CompanyResponse.from(company);
    }

    @Transactional
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
    }
}
