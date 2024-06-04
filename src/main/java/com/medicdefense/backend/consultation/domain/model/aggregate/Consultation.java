package com.medicdefense.backend.consultation.domain.model.aggregate;

import com.medicdefense.backend.consultation.domain.model.commands.CreateConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.valueobjects.MedicDefenseConsultationRecordId;
import com.medicdefense.backend.consultation.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.sql.Date;

@Getter
@Entity
public class Consultation extends AuditableAbstractAggregateRoot<Consultation> {

    @Embedded
    @Column(name = "medic_defense_consultation_id")
    private final MedicDefenseConsultationRecordId medicDefenseConsultationRecordId;

    private Date LastConsultation;

    @Embedded
    private ProfileId medicId;

    @Embedded
    private ProfileId lawyerId;

    public Consultation() {
        this.medicDefenseConsultationRecordId = new MedicDefenseConsultationRecordId();
        this.LastConsultation = new Date(System.currentTimeMillis());
    }

    public Consultation(Date date, Long medicId, Long lawyerId) {
        this();
        this.lawyerId = new ProfileId(lawyerId);
        this.medicId = new ProfileId(medicId);
        this.LastConsultation = date;
    }

    public Consultation(CreateConsultationCommand command) {
        this();
        this.lawyerId = command.lawyerId();
        this.medicId = command.medicId();
        this.LastConsultation = command.date();
    }

    public Consultation updateInformation(Date date) {
        this.LastConsultation = date;
        return this;
    }

    public String getMedicDefenseConsultationRecordId() {
        return this.medicDefenseConsultationRecordId.consultationRecordId();
    }

    public Long getMedicID() {
        return this.medicId.profileId();
    }

    public Long getLawyerID() {
        return this.lawyerId.profileId();
    }
}
