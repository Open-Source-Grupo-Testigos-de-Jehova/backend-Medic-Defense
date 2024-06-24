package com.medicdefense.backend.legalcase.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * This class represents the contact information of a person.
 */

@Embeddable
public record Contact(String phone, String email) {

    public Contact() {
        this(null, null);
    }

    public Contact {
        if (phone == null || phone.isBlank())
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or empty");
    }
}
