package com.medicdefense.backend.consultation.domain.model.valueobjects;

public record MedicDefenseId(String medicDefenseId) {
    public MedicDefenseId {
        if (medicDefenseId != null && medicDefenseId.isBlank()) {
            throw new IllegalArgumentException("MedicDefenseId cannot be empty");
        }
    }

    public MedicDefenseId() {
        this(null);
    }
}
