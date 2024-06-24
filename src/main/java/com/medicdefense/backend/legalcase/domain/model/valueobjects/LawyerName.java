package com.medicdefense.backend.legalcase.domain.model.valueobjects;


import jakarta.persistence.Embeddable;

/**
 * This class represents the name of a lawyer.
 */

@Embeddable
public record LawyerName(String firstName, String lastName) {

    public LawyerName() {this(null, null);}

    public LawyerName {
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("First name cannot be null or empty");
        if (lastName == null || lastName.isBlank())
            throw new IllegalArgumentException("Last name cannot be null or empty");
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
