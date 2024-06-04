package com.medicdefense.backend.consultation.domain.model.queries;

import com.medicdefense.backend.consultation.domain.model.valueobjects.MedicDefenseConsultationRecordId;

public record GetConsultationsByMedicDefenseRecordIdQuery(MedicDefenseConsultationRecordId consultationRecordId) {
}
