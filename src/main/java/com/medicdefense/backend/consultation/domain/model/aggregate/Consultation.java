package com.medicdefense.backend.consultation.domain.model.aggregate;

import com.medicdefense.backend.consultation.domain.model.commands.CreateConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.sql.Date;

@Getter
@Entity
public class Consultation extends AuditableAbstractAggregateRoot<Consultation> {

    private Date LastConsultation;

    @Embedded
    private ProfileId medicID;

    @Embedded
    private ProfileId lawyerID;

    public Consultation() {
        this.LastConsultation = new Date(System.currentTimeMillis());
    }

    public Consultation(Date date, Long medicID, Long lawyerID) {
        this.lawyerID = new ProfileId(lawyerID);
        this.medicID = new ProfileId(medicID);
        this.LastConsultation = date;
    }

    public Consultation(CreateConsultationCommand command) {
        this();
        this.lawyerID = command.lawyerId();
        this.medicID = command.medicId();
        this.LastConsultation = command.date();
    }

    public Consultation updateInformation(Date date) {
        this.LastConsultation = date;
        return this;
    }

    public Long getMedicID() {
        return this.medicID.profileId();
    }

    public Long getLawyerID() {
        return this.lawyerID.profileId();
    }
}
