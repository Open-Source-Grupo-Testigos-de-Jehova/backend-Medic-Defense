package com.medicdefense.backend.consultation.domain.model.commands;

import com.medicdefense.backend.consultation.domain.model.valueobjects.MedicDefenseId;

public record CreateLegalConsultationCommand(MedicDefenseId medicId, MedicDefenseId lawyerId) {
}
