package com.medicdefense.backend.legalcase.domain.model.aggregates;

import com.medicdefense.backend.legalcase.domain.model.commands.CreateLegalCaseCommand;
import com.medicdefense.backend.legalcase.domain.model.valueobjects.LegalCaseStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * This class represents a legal case.
 */

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LegalCase extends AbstractAggregateRoot<LegalCase> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 120)
    private String description;

    @Column(nullable = false)
    private LegalCaseStatus status;

    @NotNull
    @Size(max = 120)
    private String medicRecordId;

    @NotNull
    @Size(max = 120)
    private String lawyerRecordId;

    protected LegalCase() {
    }

    public LegalCase(String description, String medicRecordId, String lawyerRecordId) {
        this.description = description;
        this.status = LegalCaseStatus.OPEN;
        this.medicRecordId = medicRecordId;
        this.lawyerRecordId = lawyerRecordId;
    }

    public LegalCase(CreateLegalCaseCommand command) {
        this.description = command.description();
        this.status = LegalCaseStatus.OPEN;
        this.medicRecordId = command.clientId();
        this.lawyerRecordId = command.lawyerId();
    }

    public void close() {
        this.status = LegalCaseStatus.CLOSED;
    }

}