package com.medicdefense.backend.consultation.domain.model.entities;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalIssue;
import com.medicdefense.backend.consultation.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.sql.Date;

@Getter
@Entity
public class MessageItem extends AuditableModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date date;

    @ManyToOne
    @JoinColumn(name = "legal_issue_id")
    @NotNull
    private LegalIssue legalIssue;

    @NotNull
    private String message;

    @NotNull
    private Long senderId;

    public MessageItem(Date date, LegalIssue legalIssue, String message, Long senderId) {
        this.date = date;
        this.legalIssue = legalIssue;
        this.message = message;
        this.senderId = senderId;
    }

    public MessageItem() {
        this.date = new Date(System.currentTimeMillis());
        this.legalIssue = null;
        this.message = "";
        this.senderId = 0L;
    }

}
