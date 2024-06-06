package com.medicdefense.backend.legalcase.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Specialization(String specialization) {

    public Specialization() {
        this(null);
    }

    public Specialization {
        if (specialization == null || specialization.isBlank())
            throw new IllegalArgumentException("Area of practice cannot be null or empty");
    }
}
