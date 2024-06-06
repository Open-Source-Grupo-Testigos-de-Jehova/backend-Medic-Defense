package com.medicdefense.backend.consultation.application.internal.commandservices;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.commands.CreateLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.commands.DeleteLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.commands.UpdateLegalConsultationCommand;
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
    public Optional<LegalConsultation> handle(UpdateLegalConsultationCommand command) {
        var result = legalConsultationRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Consultation does not exist");
        var consultationToUpdate = result.get();
        try {
            var updatedConsultation = legalConsultationRepository.save(consultationToUpdate.updateInformation(command.date()));
            return Optional.of(updatedConsultation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating consultation: " + e.getMessage());
        }
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
}
