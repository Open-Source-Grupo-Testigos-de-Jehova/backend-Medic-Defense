package com.medicdefense.backend.legalcase.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * This class represents the contact information of a person.
 */

@Embeddable
public record YearsOfExperience(int years) {

    public YearsOfExperience() {
        this(0);
    }

    public YearsOfExperience {
        if (years < 0)
            throw new IllegalArgumentException("Years of experience cannot be negative");
    }

    public boolean isExperienced() {
        return years > 5;
    }
}