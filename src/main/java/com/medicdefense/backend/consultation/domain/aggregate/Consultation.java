package com.medicdefense.backend.consultation.domain.aggregate;

import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class Consultation extends AuditableAbstractAggregateRoot<Consultation> {
    private String title;
    private String description;

    public Consultation() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
    }
}
