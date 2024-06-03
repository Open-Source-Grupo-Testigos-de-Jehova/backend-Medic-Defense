package com.medicdefense.backend.communication.domain.model.aggregates;

import com.medicdefense.backend.communication.domain.model.valueobjects.Message;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class Notification extends AuditableAbstractAggregateRoot<Notification> {

    @Embedded
    private Message message;

    public Notification(String message) {
        this.message = new Message(message);
    }

    public Notification() {
    }

    public void updateMessage(String message){
        this.message = new Message(message);
    }

    public String getMessage() {
        return message.getMessage();
    }

}
