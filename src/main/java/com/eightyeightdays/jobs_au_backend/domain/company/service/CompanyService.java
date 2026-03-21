package com.eightyeightdays.jobs_au_backend.domain.company.service;

import com.eightyeightdays.jobs_au_backend.domain.company.dto.CompanySearchRequest;
import com.eightyeightdays.jobs_au_backend.domain.company.dto.CreateCompanyRequest;
import com.eightyeightdays.jobs_au_backend.domain.company.dto.UpdateCompanyRequest;
import com.eightyeightdays.jobs_au_backend.domain.company.dto.CompanyResponse;
import com.eightyeightdays.jobs_au_backend.domain.company.entity.Company;
import com.eightyeightdays.jobs_au_backend.domain.company.repository.CompanyRepository;
import com.eightyeightdays.jobs_au_backend.domain.company.repository.CompanySpecification;
import com.eightyeightdays.jobs_au_backend.domain.geocode.service.PlaceService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final PlaceService placeService;

    @Transactional
    public void create(CreateCompanyRequest request) {
        
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

    public List<CompanyResponse> getCompanies(CompanySearchRequest req, Pageable pageable) {

        Specification<Company> spec = (root, query, cb) -> cb.conjunction();

        if (req.category() != null) {
            spec = spec.and(CompanySpecification.hasCategory(req.category()));
        }

        if (req.state() != null) {
            spec = spec.and(CompanySpecification.hasState(req.state()));
        }

        if (req.season() != null) {
            spec = spec.and(CompanySpecification.hasSeason(req.season()));
        }

        if (req.cropType() != null) {
            spec = spec.and(CompanySpecification.hasCropType(req.cropType()));
        }

        Page<Company> companies = companyRepository.findAll(spec, pageable);

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
    public CompanyResponse put(Long id, UpdateCompanyRequest request) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Company not found"));

        company.update(request);

        return CompanyResponse.from(company);
    }

    @Transactional
    public CompanyResponse patch(Long id, UpdateCompanyRequest request) {

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
