package com.medicdefense.backend.communication.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Message( String message) {

    public Message() { this(null); }

    public Message {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be null");
        }
    }

    public String getMessage(){
        return String.format("%s", message);
    }
}
