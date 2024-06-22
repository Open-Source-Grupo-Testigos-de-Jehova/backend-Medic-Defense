package com.medicdefense.backend.consultation.application.internal.commandservices;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.commands.*;
import com.medicdefense.backend.consultation.domain.model.entities.LegalIssueItem;
import com.medicdefense.backend.consultation.domain.services.LegalConsultationCommandService;
import com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories.LegalConsultationRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LegalConsultationCommandServiceImpl implements LegalConsultationCommandService {

    private final LegalConsultationRepository legalConsultationRepository;


    public LegalConsultationCommandServiceImpl(LegalConsultationRepository legalConsultationRepository) {
        this.legalConsultationRepository = legalConsultationRepository;
    }

    @Override
    public Long handle(CreateLegalConsultationCommand command) {
        if(legalConsultationRepository.existsByLawyerIdAndMedicId(command.lawyerId(), command.medicId())){
            throw new IllegalArgumentException("Consultation with same id already exists");
        }
        var legalConsultation = new LegalConsultation(command);
        try {
            legalConsultationRepository.save(legalConsultation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving consultation: " + e.getMessage());
        }
        return legalConsultation.getId();
    }

    @Override
    public void handle(DeleteLegalConsultationCommand command) {
        if (!legalConsultationRepository.existsById(command.LegalConsultationId())) {
            throw new IllegalArgumentException("Consultation does not exist");
        }
        try {
            legalConsultationRepository.deleteById(command.LegalConsultationId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting consultation: " + e.getMessage());
        }
    }

    @Override
    public void handle(AskLegalIssueCommand command) {
        var result = legalConsultationRepository.findById(command.legalConsultationId());
        if (result.isEmpty()) throw new IllegalArgumentException("Consultation does not exist");
        try {
            result.map(
                    legalConsultation -> {
                        legalConsultation.addLegalIssue(command.Consultation());
                        legalConsultationRepository.save(legalConsultation);
                        System.out.println("Legal issue added");
                        return legalConsultation;
                    });
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while asking issue: " + e.getMessage());
        }
    }

    @Override
    public void handle(CloseLegalIssueCommand command) {
        var result = legalConsultationRepository.findById(command.legalConsultationId());
        if (result.isEmpty()) throw new IllegalArgumentException("Consultation does not exist");
        try {
            result.map(
                    legalConsultation -> {
                        legalConsultation.closeLegalIssue(command.legalIssueId());
                        legalConsultationRepository.save(legalConsultation);
                        System.out.println("Legal issue closed");
                        return legalConsultation;
                    });
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while closing issue: " + e.getMessage());
        }
    }

    @Override
    public void handle(RespondLegalIssueCommand command) {
        var result = legalConsultationRepository.findById(command.legalConsultationId());
        if (result.isEmpty()) throw new IllegalArgumentException("Consultation does not exist");
        try {
            result.map(
                    legalConsultation -> {
                        legalConsultation.respondToLegalIssue(command.legalIssueId(), command.response());
                        legalConsultationRepository.save(legalConsultation);
                        System.out.println("Legal issue responded");
                        return legalConsultation;
                    });
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while responding issue: " + e.getMessage());
        }
    }
}
