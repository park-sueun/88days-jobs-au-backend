package com.eightyeightdays.jobs_au_backend.user.model;

import com.eightyeightdays.jobs_au_backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "employer_profiles",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_employer_profiles_company_id",
                        columnNames = {"user_id", "company_id"}
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployerProfile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1:1 User
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_employer_profiles_user")
    )
    private User user;

//    // 1:1 Company
//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(
//            name = "company_id",
//            nullable = true,
//            foreignKey = @ForeignKey(name = "fk_employer_profiles_company")
//    )
//    private Company company;

    @Column(name = "position")
    private String position;

}
