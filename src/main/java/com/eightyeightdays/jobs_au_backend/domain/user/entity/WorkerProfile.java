package com.eightyeightdays.jobs_au_backend.domain.user.entity;

import com.eightyeightdays.jobs_au_backend.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "worker_profiles",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_worker_profiles_user_id",
                        columnNames = "user_id"
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkerProfile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1:1 User
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_worker_profiles_user")
    )
    private User user;

    @Column(name = "nationality")
    private String nationality;

    @Enumerated(EnumType.STRING)
    @Column(name = "visa_type", length = 50)
    private VisaType visaType;

    @Column(name = "visa_expiry_date")
    private LocalDate visaExpiryDate;

    private WorkerProfile(
            User user
    ) {
        this.user = user;
    }

    public static WorkerProfile of(
            User user
    ) {

        return new WorkerProfile(user);
    }

}
