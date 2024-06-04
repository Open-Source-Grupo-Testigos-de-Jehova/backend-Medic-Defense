package com.medicdefense.backend.consultation.domain.services;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.commands.CreateLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.commands.DeleteLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.commands.UpdateLegalConsultationCommand;

import java.util.Optional;

public interface ConsultationCommandService {
    Long handle(CreateLegalConsultationCommand command);
    Optional<LegalConsultation> handle(UpdateLegalConsultationCommand command);
    void handle(DeleteLegalConsultationCommand command);
}
