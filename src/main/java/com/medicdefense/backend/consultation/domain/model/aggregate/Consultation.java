package com.medicdefense.backend.consultation.domain.model.aggregate;

import com.medicdefense.backend.consultation.domain.model.commands.CreateConsultationCommand;
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

    public Consultation(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }

    public Consultation(CreateConsultationCommand command) {
        this();
        this.title = command.title();
        this.description = command.description();
    }


}
