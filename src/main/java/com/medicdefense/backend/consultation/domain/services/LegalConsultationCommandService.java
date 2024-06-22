package com.medicdefense.backend.consultation.domain.services;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.commands.*;

import java.util.Optional;

public interface LegalConsultationCommandService {
    Long handle(CreateLegalConsultationCommand command);
    void handle(DeleteLegalConsultationCommand command);
    void handle(AskLegalIssueCommand command);
    void handle(CloseLegalIssueCommand command);
    void handle(RespondLegalIssueCommand command);
}
