package com.eightyeightdays.jobs_au_backend.company.model;

import com.eightyeightdays.jobs_au_backend.global.entity.BaseTimeEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String contactNumber;
    private String email;
    private String website;
    private String description;

    private String unit;
    private String street;
    private String suburb;

    @Enumerated(EnumType.STRING)
    private State state;

    private String postcode;
    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private WorkType workType;

    private String cropType;

    private boolean is_visa_extension_eligible;

    @Enumerated(EnumType.STRING)
    private Status status;
}