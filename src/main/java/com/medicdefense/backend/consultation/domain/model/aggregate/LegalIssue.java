package com.medicdefense.backend.consultation.domain.model.aggregate;

import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.JoinColumn;
import lombok.Getter;

public class LegalIssue extends AuditableAbstractAggregateRoot<LegalIssue> {

    @Getter
    @Embedded
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;


}
