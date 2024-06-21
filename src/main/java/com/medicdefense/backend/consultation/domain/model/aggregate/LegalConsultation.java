package com.medicdefense.backend.consultation.domain.model.aggregate;

import com.medicdefense.backend.consultation.domain.model.commands.CreateLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.valueobjects.MedicDefenseId;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Date;

@Getter
@Entity
public class LegalConsultation extends AuditableAbstractAggregateRoot<LegalConsultation> {

    private Date lastConsultation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "medicDefenseId", column = @Column(name = "medic_profile_id"))
    })
    private MedicDefenseId medicId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "medicDefenseId", column = @Column(name = "lawyer_profile_id"))
    })
    private MedicDefenseId lawyerId;

    public LegalConsultation() {
        this.lastConsultation = new Date(System.currentTimeMillis());
    }

    public LegalConsultation(String medicId, String lawyerId) {
        this();
        this.lawyerId = new MedicDefenseId(lawyerId);
        this.medicId = new MedicDefenseId(medicId);
        this.lastConsultation = new Date(System.currentTimeMillis());
    }

    public LegalConsultation(CreateLegalConsultationCommand command) {
        this();
        this.lawyerId = command.lawyerId();
        this.medicId = command.medicId();
        this.lastConsultation = new Date(System.currentTimeMillis());
    }

    public LegalConsultation updateInformation(Date date) {
        this.lastConsultation = new Date(System.currentTimeMillis());
        return this;
    }

    public String getMedicID() {
        return this.medicId.medicDefenseId();
    }

    public String getLawyerID() {
        return this.lawyerId.medicDefenseId();
    }
}
