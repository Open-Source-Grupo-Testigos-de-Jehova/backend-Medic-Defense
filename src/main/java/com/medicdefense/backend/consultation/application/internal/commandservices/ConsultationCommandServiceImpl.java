package com.medicdefense.backend.consultation.application.internal.commandservices;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.commands.CreateLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.commands.DeleteLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.commands.UpdateLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.services.ConsultationCommandService;
import com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories.ConsultationRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultationCommandServiceImpl implements ConsultationCommandService {

    private final ConsultationRepository consultationRepository;


    public ConsultationCommandServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Long handle(CreateLegalConsultationCommand command) {
        if(consultationRepository.existsByLawyerIdAndMedicId(command.lawyerId(), command.medicId())){
            throw new IllegalArgumentException("Consultation with same id already exists");
        }
        var legalConsultation = new LegalConsultation(command);
        try {
            consultationRepository.save(legalConsultation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving consultation: " + e.getMessage());
        }
        return legalConsultation.getId();
    }

    @Override
    public Optional<LegalConsultation> handle(UpdateLegalConsultationCommand command) {
        var result = consultationRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Consultation does not exist");
        var consultationToUpdate = result.get();
        try {
            var updatedConsultation = consultationRepository.save(consultationToUpdate.updateInformation(command.date()));
            return Optional.of(updatedConsultation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating consultation: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteLegalConsultationCommand command) {
        if (!consultationRepository.existsById(command.LegalConsultationId())) {
            throw new IllegalArgumentException("Consultation does not exist");
        }
        try {
            consultationRepository.deleteById(command.LegalConsultationId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting consultation: " + e.getMessage());
        }
    }
}
