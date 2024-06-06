package com.medicdefense.backend.legalcase.domain.model.aggregates;

import com.medicdefense.backend.legalcase.domain.model.valueobjects.*;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;


public class Lawyer extends AuditableAbstractAggregateRoot<Lawyer> {

    @Embedded
    private LawyerName name;

    @Embedded
    private Specialization specialization;

    @Embedded
    private PricePerHour pricePerHour;

    @Embedded
    private Contact contact;

    @Embedded
    private YearsOfExperience yearsOfExperience;

    protected Lawyer() {
    }

    // Constructor with all attributes
    public Lawyer(LawyerName name, Specialization specialization, PricePerHour pricePerHour, Contact contact, YearsOfExperience yearsOfExperience) {
        this.name = name;
        this.specialization = specialization;
        this.pricePerHour = pricePerHour;
        this.contact = contact;
        this.yearsOfExperience = yearsOfExperience;
    }


    public String getFullName() {
        return name.getFullName();
    }

    public String getSpecialization() {
        return specialization.specialization();
    }

    public String getPricePerHour() {
        return pricePerHour.formattedPrice();
    }

    public String getContact() {
        return contact.email();
    }

    public boolean getYearsOfExperience() {
        return yearsOfExperience.isExperienced();
    }


    public boolean isAvailable() {
        return true;
    }

    public void updateContact(Contact newContact) {
        if (newContact == null) {
            throw new IllegalArgumentException("New contact cannot be null");
        }
        this.contact = newContact;
    }

    public void updatePricePerHour(PricePerHour newPricePerHour) {
        if (newPricePerHour == null) {
            throw new IllegalArgumentException("New price per hour cannot be null");
        }
        this.pricePerHour = newPricePerHour;
    }

    public void updateSpecialization(Specialization newSpecialization) {
        if (newSpecialization == null) {
            throw new IllegalArgumentException("New specialization cannot be null");
        }
        this.specialization = newSpecialization;
    }

    public void updateYearsOfExperience(YearsOfExperience newYearsOfExperience) {
        if (newYearsOfExperience == null) {
            throw new IllegalArgumentException("New years of experience cannot be null");
        }
        this.yearsOfExperience = newYearsOfExperience;
    }
}
