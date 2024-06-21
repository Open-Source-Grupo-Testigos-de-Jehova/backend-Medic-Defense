package com.medicdefense.backend.consultation.domain.model.queries;

import com.medicdefense.backend.consultation.domain.model.valueobjects.MedicDefenseId;

public record GetLegalConsultationsByMedicIdQuery(MedicDefenseId medicId) {
}
