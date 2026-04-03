package com.eightyeightdays.jobs_au_backend.domain.place.dto;

import com.eightyeightdays.jobs_au_backend.domain.place.entity.Contact;

public record ContactResponse(
        String name,
        String phone,
        String email
) {

    public static ContactResponse from(Contact contact) {
        return new ContactResponse(
                contact.getName(),
                contact.getPhone(),
                contact.getEmail()
        );
    }
}
