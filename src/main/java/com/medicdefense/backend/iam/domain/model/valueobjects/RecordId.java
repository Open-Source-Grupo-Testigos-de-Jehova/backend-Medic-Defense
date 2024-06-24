package com.medicdefense.backend.iam.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record RecordId(String recordId) {

    public RecordId() {
        this("");
    }
}
