package com.medicdefense.backend.payment.application.internal.commandservices;

import com.medicdefense.backend.payment.domain.model.aggregates.Consultation;
import com.medicdefense.backend.payment.domain.model.commands.CreateConsultationCommand;
import com.medicdefense.backend.payment.domain.services.ConsultationCommandService;
import com.medicdefense.backend.payment.infrastructure.persistence.jpa.ConsultationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ConsultationCommandServiceImpl implements ConsultationCommandService {

    private final ConsultationRepository consultationRepository;

    public ConsultationCommandServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    @Transactional
    public Optional<Consultation> handle(CreateConsultationCommand command) {
        Consultation consultation = new Consultation(command.date(), command.legalIssue());
        return Optional.of(consultationRepository.save(consultation));
    }
}