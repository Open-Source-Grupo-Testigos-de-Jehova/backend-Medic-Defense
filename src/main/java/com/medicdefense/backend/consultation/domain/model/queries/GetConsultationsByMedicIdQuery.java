package com.medicdefense.backend.consultation.domain.model.queries;

import com.medicdefense.backend.consultation.domain.model.valueobjects.ProfileId;

public record GetConsultationsByMedicIdQuery(ProfileId medicId) {
}
