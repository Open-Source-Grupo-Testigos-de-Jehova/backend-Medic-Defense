package com.medicdefense.backend.legalcase.domain.model.aggregates;

import com.medicdefense.backend.legalcase.domain.model.commands.CreateLegalCaseCommand;
import jakarta.persistence.*;
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

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    protected LegalCase() {
    }

    public LegalCase(CreateLegalCaseCommand command) {
        this.caseNumber = command.caseNumber();
        this.description = command.description();
        this.status = command.status();
    }
}