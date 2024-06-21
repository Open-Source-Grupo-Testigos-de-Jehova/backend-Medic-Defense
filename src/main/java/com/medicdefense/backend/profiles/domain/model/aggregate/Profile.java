package com.medicdefense.backend.profiles.domain.model.aggregate;

import com.medicdefense.backend.profiles.domain.model.commands.CreateProfileCommand;
import com.medicdefense.backend.profiles.domain.model.valueobjects.EmailAddress;
import com.medicdefense.backend.profiles.domain.model.valueobjects.PersonName;
import com.medicdefense.backend.profiles.domain.model.valueobjects.Specialities;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Embedded
    private PersonName name;

    @Embedded
    private EmailAddress email;

    @Getter
    @NotNull
    private String DNI;

    @Getter
    @NotNull
    private String image_url;

    @Getter
    @Embedded
    private Specialities specialities;

    @Getter
    @NotNull
    private String phoneNumber;

    public Profile(String firstName, String lastName, String email, String phoneNumber, String DNI, String image_url) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.phoneNumber = phoneNumber;
        this.DNI = DNI;
        this.image_url = image_url;
    }

    public Profile(CreateProfileCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.phoneNumber = command.phoneNumber();
        this.DNI = command.DNI();
        this.image_url = command.image_url();
    }
    public Profile() {

    }

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() { return name.getFullName(); }

    public String getEmailAddress() { return email.address(); }

    public void addSpeciality(String speciality) {
        this.specialities.addSpecialityItem(speciality);
    }
}
