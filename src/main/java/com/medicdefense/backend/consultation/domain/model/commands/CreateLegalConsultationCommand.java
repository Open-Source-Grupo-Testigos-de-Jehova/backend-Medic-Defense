package com.medicdefense.backend.consultation.domain.model.commands;

import com.medicdefense.backend.consultation.domain.model.valueobjects.ProfileId;

public record CreateLegalConsultationCommand(ProfileId medicId, ProfileId lawyerId) {
}
