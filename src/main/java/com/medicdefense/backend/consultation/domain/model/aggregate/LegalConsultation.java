package com.medicdefense.backend.consultation.domain.model.aggregate;

import com.medicdefense.backend.consultation.domain.model.commands.CreateLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.valueobjects.LegalIssue;
import com.medicdefense.backend.consultation.domain.model.valueobjects.MedicDefenseId;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Date;

@Getter
@Entity
public class LegalConsultation extends AuditableAbstractAggregateRoot<LegalConsultation> {

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

    @Embedded
    private final LegalIssue legalIssue;

    public LegalConsultation() {
        this.legalIssue = new LegalIssue();
        this.medicId = new MedicDefenseId();
        this.lawyerId = new MedicDefenseId();
    }

    public LegalConsultation(String medicId, String lawyerId, String issue) {
        this();
        this.lawyerId = new MedicDefenseId(lawyerId);
        this.medicId = new MedicDefenseId(medicId);
        this.legalIssue.addLegalIssueItem(issue, this);
    }

    public LegalConsultation(CreateLegalConsultationCommand command) {
        this();
        this.lawyerId = command.lawyerId();
        this.medicId = command.medicId();
        this.legalIssue.addLegalIssueItem(command.Issue(), this);
    }

    public String getMedicID() {
        return this.medicId.medicDefenseId();
    }

    public String getLawyerID() {
        return this.lawyerId.medicDefenseId();
    }

    public void addLegalIssue(String issue) {
        this.legalIssue.addLegalIssueItem(issue, this);
    }

    public void closeLegalIssue(Long Id) {
        this.legalIssue.closeLegalIssueItemById(Id);
    }

    public void respondToLegalIssue(Long Id, String response) {
        this.legalIssue.responseLegalIssueItemById(Id, response);
    }
}

