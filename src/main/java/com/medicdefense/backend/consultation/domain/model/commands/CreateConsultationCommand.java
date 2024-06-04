package com.medicdefense.backend.consultation.domain.model.commands;

import com.medicdefense.backend.consultation.domain.model.valueobjects.ProfileId;

import java.sql.Date;

public record CreateConsultationCommand(ProfileId medicId, ProfileId lawyerId, Date date) {
}
