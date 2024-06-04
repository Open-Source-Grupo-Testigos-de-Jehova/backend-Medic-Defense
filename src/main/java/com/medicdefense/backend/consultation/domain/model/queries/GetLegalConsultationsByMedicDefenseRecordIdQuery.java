package com.medicdefense.backend.consultation.domain.model.queries;

import com.medicdefense.backend.consultation.domain.model.valueobjects.MedicDefenseLegalConsultationRecordId;

public record GetLegalConsultationsByMedicDefenseRecordIdQuery(
        MedicDefenseLegalConsultationRecordId consultationRecordId) {
}
