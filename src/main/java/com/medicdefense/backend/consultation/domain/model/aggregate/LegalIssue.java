package com.medicdefense.backend.consultation.domain.model.aggregate;

import com.medicdefense.backend.consultation.domain.model.commands.AskLegalIssueCommand;
import com.medicdefense.backend.consultation.domain.model.valueobjects.LegalIssueStatus;
import com.medicdefense.backend.consultation.domain.model.valueobjects.Messages;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class LegalIssue extends AuditableAbstractAggregateRoot<LegalIssue> {

    private String title;

    private String firstMessage;

    @Getter
    @ManyToOne
    @JoinColumn(name = "legal_consultation_id")
    private LegalConsultation legalConsultation;

    private LegalIssueStatus status;

    @Embedded
    private final Messages messages;

    public LegalIssue(AskLegalIssueCommand command, LegalConsultation legalConsultation) {
        this.status = LegalIssueStatus.OPEN;
        this.title = command.title();
        this.legalConsultation = legalConsultation;
        this.firstMessage = command.firstMessage();
        this.messages = new Messages();
        addMessage(command.firstMessage(), legalConsultation.getLawyerID());
    }

    public LegalIssue(LegalConsultation legalConsultation, String title, String firstMessage) {
        this.status = LegalIssueStatus.OPEN;
        this.title = title;
        this.firstMessage = firstMessage;
        this.messages = new Messages();
        addMessage(firstMessage, legalConsultation.getLawyerID());
        this.legalConsultation = new LegalConsultation();
    }

    public LegalIssue() {
        this.status = LegalIssueStatus.OPEN;
        this.title = "";
        this.messages = new Messages();
    }

    public void close() {
        this.status = LegalIssueStatus.CLOSED;
    }

    public void addMessage(String message, Long senderId) {
        this.messages.addMessageItems(this, message, senderId);
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

    public Long getLawyerId() {
        return this.legalConsultation.getLawyerID();
    }
}
