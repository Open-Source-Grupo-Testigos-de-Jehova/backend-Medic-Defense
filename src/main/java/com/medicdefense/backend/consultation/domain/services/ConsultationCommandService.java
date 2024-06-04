package com.medicdefense.backend.consultation.domain.services;

import com.medicdefense.backend.consultation.domain.model.commands.CreateConsultationCommand;

public interface ConsultationCommandService {
Long handle(CreateConsultationCommand command);
}
