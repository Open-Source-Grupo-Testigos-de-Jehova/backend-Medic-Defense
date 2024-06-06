package com.medicdefense.backend.payment.domain.services;

import com.medicdefense.backend.payment.domain.model.aggregates.Consultation;
import com.medicdefense.backend.payment.domain.model.commands.CreateConsultationCommand;

import java.util.Optional;

public interface ConsultationCommandService {
    Optional<Consultation> handle(CreateConsultationCommand command);
}