package com.medicdefense.backend.consultation.domain.model.commands;

import java.sql.Date;

public record UpdateConsultationCommand(Long id, Date date) {
}
