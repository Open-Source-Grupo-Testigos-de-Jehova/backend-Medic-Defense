package com.medicdefense.backend.consultation.domain.model.valueobjects;

import java.util.UUID;

public record MedicDefenseConsultationRecordId(String consultationRecordId) {
    public MedicDefenseConsultationRecordId() {
        this(UUID.randomUUID().toString());
    }

    public MedicDefenseConsultationRecordId {
        if (consultationRecordId == null || consultationRecordId.isBlank()) {
            throw new IllegalArgumentException("Medic Defense consultation record profileId cannot be null or blank");
        }
    }
}
