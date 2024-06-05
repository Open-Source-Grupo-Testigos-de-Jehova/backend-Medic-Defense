package com.medicdefense.backend.consultation.domain.model.aggregate;

import com.medicdefense.backend.consultation.domain.model.commands.CreateLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.sql.Date;

@Getter
@Entity
public class LegalConsultation extends AuditableAbstractAggregateRoot<LegalConsultation> {

    private Date LastConsultation;

    @Embedded
    private ProfileId medicId;

    @Embedded
    private ProfileId lawyerId;

    public LegalConsultation() {
        this.LastConsultation = new Date(System.currentTimeMillis());
    }

    public LegalConsultation(Date date, Long medicId, Long lawyerId) {
        this();
        this.lawyerId = new ProfileId(lawyerId);
        this.medicId = new ProfileId(medicId);
        this.LastConsultation = date;
    }

    public LegalConsultation(CreateLegalConsultationCommand command) {
        this();
        this.lawyerId = command.lawyerId();
        this.medicId = command.medicId();
        this.LastConsultation = new Date(System.currentTimeMillis());
    }

    public LegalConsultation updateInformation(Date date) {
        this.LastConsultation = date;
        return this;
    }

    public Long getMedicID() {
        return this.medicId.profileId();
    }

    public Long getLawyerID() {
        return this.lawyerId.profileId();
    }
}
