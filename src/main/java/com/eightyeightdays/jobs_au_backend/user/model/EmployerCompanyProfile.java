package com.eightyeightdays.jobs_au_backend.user.model;

import com.eightyeightdays.jobs_au_backend.company.model.Company;
import com.eightyeightdays.jobs_au_backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "employer_company_profiles",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "uq_employer_company",
                    columnNames = {"user_id", "company_id"}
            )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployerCompanyProfile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private String position;
}
