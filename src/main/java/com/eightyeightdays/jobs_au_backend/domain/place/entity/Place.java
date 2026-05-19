package com.eightyeightdays.jobs_au_backend.domain.place.entity;

import com.eightyeightdays.jobs_au_backend.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name = "idx_place_lat_lng", columnList = "latitude, longitude"),
        @Index(name = "idx_place_status", columnList = "status")
})
public class Place extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String website;
    private String description;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    private String state;
    private String region;
    private String postcode;
    private String address;
    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private Category category;
    private String season;
    private String cropType;
    private String payRate;

    private Boolean isVisaExtensionEligible;
    private Boolean hasAccommodation;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @lombok.Builder
    private Place(
            String name, String website, String description,
            String state, String region, String postcode, String address,
            Double latitude, Double longitude,
            Category category, String season, String cropType, String payRate,
            Boolean isVisaExtensionEligible, Boolean hasAccommodation,
            Status status
    ) {
        this.name = name;
        this.website = website;
        this.description = description;
        this.state = state;
        this.region = region;
        this.postcode = postcode;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.season = season;
        this.cropType = cropType;
        this.payRate = payRate;
        this.isVisaExtensionEligible = isVisaExtensionEligible;
        this.hasAccommodation = hasAccommodation;
        this.status = status;
    }

    public void update(com.eightyeightdays.jobs_au_backend.domain.place.dto.PlaceRequest request) {
        this.name = request.name();
        this.website = request.website();
        this.description = request.description();
        this.state = request.state();
        this.region = request.region();
        this.postcode = request.postcode();
        this.address = request.address();
        this.latitude = request.latitude();
        this.longitude = request.longitude();
        this.category = request.category();
        this.season = request.season();
        this.cropType = request.cropType();
        this.payRate = request.payRate();
        this.isVisaExtensionEligible = request.isVisaExtensionEligible();
        this.hasAccommodation = request.hasAccommodation();
        this.status = request.status();
    }

    public void updateContacts(List<Contact> newContacts) {
        this.contacts.clear();
        this.contacts.addAll(newContacts);
    }

    public void patch(com.eightyeightdays.jobs_au_backend.domain.place.dto.PlaceRequest request) {
        if (request.name() != null) this.name = request.name();
        if (request.website() != null) this.website = request.website();
        if (request.description() != null) this.description = request.description();
        if (request.state() != null) this.state = request.state();
        if (request.region() != null) this.region = request.region();
        if (request.postcode() != null) this.postcode = request.postcode();
        if (request.address() != null) this.address = request.address();
        if (request.latitude() != null) this.latitude = request.latitude();
        if (request.longitude() != null) this.longitude = request.longitude();
        if (request.category() != null) this.category = request.category();
        if (request.season() != null) this.season = request.season();
        if (request.cropType() != null) this.cropType = request.cropType();
        if (request.payRate() != null) this.payRate = request.payRate();
        if (request.isVisaExtensionEligible() != null) this.isVisaExtensionEligible = request.isVisaExtensionEligible();
        if (request.hasAccommodation() != null) this.hasAccommodation = request.hasAccommodation();
        if (request.status() != null) this.status = request.status();
    }
}
