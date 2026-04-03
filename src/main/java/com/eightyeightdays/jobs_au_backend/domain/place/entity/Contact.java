package com.eightyeightdays.jobs_au_backend.domain.place.entity;

import com.eightyeightdays.jobs_au_backend.domain.place.dto.ContactRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public static Contact of(ContactRequest request, Place place) {
        Contact contact = new Contact();
        contact.place = place;
        contact.name = request.name();
        contact.phone = request.phone();
        contact.email = request.email();
        return contact;
    }
}
