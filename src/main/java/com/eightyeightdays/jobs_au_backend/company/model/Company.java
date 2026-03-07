package com.eightyeightdays.jobs_au_backend.company.model;

import com.eightyeightdays.jobs_au_backend.company.dto.CompanyPatchRequest;
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

    @Enumerated(EnumType.STRING)
    private PayType payType;

    private String cropType;

    private Boolean isVisaExtensionEligible;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    private Company(String name, String contactNumber, String email, String website, String description, String unit, String street, String suburb, State state, String postcode, Double latitude, Double longitude, WorkType workType, PayType payType, String cropType, Boolean isVisaExtensionEligible, Status status) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.website = website;
        this.description = description;
        this.unit = unit;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.workType = workType;
        this.payType = payType;
        this.cropType = cropType;
        this.isVisaExtensionEligible = isVisaExtensionEligible;
        this.status = status;
    }

    public void patch(CompanyPatchRequest request) {

        if (request.name() != null) {
            this.name = request.name();
        }

        if (request.contactNumber() != null) {
            this.contactNumber = request.contactNumber();
        }

        if (request.email() != null) {
            this.email = request.email();
        }

        if (request.website() != null) {
            this.website = request.website();
        }

        if (request.description() != null) {
            this.description = request.description();
        }

        if (request.unit() != null) {
            this.unit = request.unit();
        }

        if (request.street() != null) {
            this.street = request.street();
        }

        if (request.suburb() != null) {
            this.suburb = request.suburb();
        }

        if (request.state() != null) {
            this.state = request.state();
        }

        if (request.postcode() != null) {
            this.postcode = request.postcode();
        }

        if (request.latitude() != null) {
            this.latitude = request.latitude();
        }

        if (request.longitude() != null) {
            this.longitude = request.longitude();
        }

        if (request.workType() != null) {
            this.workType = request.workType();
        }

        if (request.payType() != null) {
            this.payType = request.payType();
        }

        if (request.cropType() != null) {
            this.cropType = request.cropType();
        }

        if (request.isVisaExtensionEligible() != null) {
            this.isVisaExtensionEligible = request.isVisaExtensionEligible();
        }

        if (request.status() != null) {
            this.status = request.status();
        }
    }
}