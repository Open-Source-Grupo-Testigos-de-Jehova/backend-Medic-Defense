package com.medicdefense.backend.consultation.domain.model.commands;

import java.sql.Date;

public record UpdateLegalConsultationCommand(Long id, Date date) {
}
