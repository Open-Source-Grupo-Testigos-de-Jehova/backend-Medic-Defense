package com.medicdefense.backend.legalcase.domain.model.aggregates;

import com.medicdefense.backend.legalcase.domain.model.commands.CreateLegalCaseCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LegalCase extends AbstractAggregateRoot<LegalCase> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String caseNumber;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String status;

    @NotNull
    private String medicRecordId;

    @NotNull
    private String lawyerRecordId;

    protected LegalCase() {
    }

    public LegalCase(String caseNumber, String description, String status) {
        this.caseNumber = caseNumber;
        this.description = description;
        this.status = status;
    }

    public LegalCase(CreateLegalCaseCommand command) {
        this.caseNumber = command.caseNumber();
        this.description = command.description();
        this.status = command.status();
    }
}