package com.eightyeightdays.jobs_au_backend.company.repository;

import com.eightyeightdays.jobs_au_backend.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
