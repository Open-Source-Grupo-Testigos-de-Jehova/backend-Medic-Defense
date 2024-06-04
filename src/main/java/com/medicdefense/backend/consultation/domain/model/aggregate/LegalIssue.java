package com.medicdefense.backend.consultation.domain.model.aggregate;

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
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    private LegalIssueStatus status;

    @Embedded
    private final Messages messages;

    public LegalIssue(Consultation consultation, String title, String firstMessage) {
        this.status = LegalIssueStatus.OPEN;
        this.title = title;
        this.firstMessage = firstMessage;
        this.messages = new Messages();
        addMessage(firstMessage, consultation.getLawyerID());
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
}
