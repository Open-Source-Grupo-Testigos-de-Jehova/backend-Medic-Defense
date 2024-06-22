package com.medicdefense.backend.consultation.domain.model.entities;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.commands.AskLegalIssueCommand;
import com.medicdefense.backend.consultation.domain.model.valueobjects.LegalIssueStatus;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.medicdefense.backend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class LegalIssueItem extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String consultation;

    @NotNull
    private String response;

    @ManyToOne
    @JoinColumn(name = "legal_consultation_id")
    private LegalConsultation legalConsultation;

    @NotNull
    private LegalIssueStatus status;

    public LegalIssueItem(AskLegalIssueCommand command, LegalConsultation legalConsultation) {
        this.status = LegalIssueStatus.OPEN;
        this.consultation = command.Consultation();
        this.legalConsultation = legalConsultation;
        this.response = "En espera de una respuesta";
    }

    public LegalIssueItem(LegalConsultation legalConsultation, String consultation) {
        this.status = LegalIssueStatus.OPEN;
        this.consultation = consultation;
        this.legalConsultation = legalConsultation;
        this.response = "En espera de una respuesta";
    }

    public LegalIssueItem() {
        this.status = LegalIssueStatus.OPEN;
        this.consultation = "";
        this.response = "En espera de una respuesta";
        this.legalConsultation = new LegalConsultation();
    }

    public void close() {
        this.status = LegalIssueStatus.CLOSED;
    }

    public boolean isClosed() {
        return this.status == LegalIssueStatus.CLOSED;
    }

    public Long getLegalConsultationId() {
        return this.legalConsultation.getId();
    }

    public String getStatus() {
        return this.status.name().toLowerCase();
    }

    public String getLawyerId() {
        return this.legalConsultation.getLawyerID();
    }

    public void respond(String response) {
        this.response = response;
    }
}
