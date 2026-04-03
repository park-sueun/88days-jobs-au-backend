package com.eightyeightdays.jobs_au_backend.domain.place.entity;

import jakarta.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private String name;
    private String phone;
    private String email;
}
