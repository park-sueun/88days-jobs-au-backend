package com.eightyeightdays.jobs_au_backend.domain.user.repository;

import com.eightyeightdays.jobs_au_backend.domain.user.entity.EmployerCompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployerCompanyProfileRepository extends JpaRepository<EmployerCompanyProfile, Long> {
    Optional<EmployerCompanyProfile> findByUserId(Long userId);
    List<EmployerCompanyProfile> findAllByUserId(Long userId);
}
