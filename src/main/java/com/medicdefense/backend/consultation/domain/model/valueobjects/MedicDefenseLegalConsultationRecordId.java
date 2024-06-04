package com.medicdefense.backend.consultation.domain.model.valueobjects;

import java.util.UUID;

public record MedicDefenseLegalConsultationRecordId(String consultationRecordId) {
    public MedicDefenseLegalConsultationRecordId() {
        this(UUID.randomUUID().toString());
    }

    public MedicDefenseLegalConsultationRecordId {
        if (consultationRecordId == null || consultationRecordId.isBlank()) {
            throw new IllegalArgumentException("Medic Defense consultation record profileId cannot be null or blank");
        }
    }
}
