package com.medicdefense.backend.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record MedicDefenseRecordId(String RecordId) {
    public MedicDefenseRecordId() {
        this(UUID.randomUUID().toString());
    }

    public MedicDefenseRecordId {
        if (RecordId == null || RecordId.isBlank()) {
            throw new IllegalArgumentException("Medic defense record medicDefenseId cannot be null or blank");
        }
    }
}