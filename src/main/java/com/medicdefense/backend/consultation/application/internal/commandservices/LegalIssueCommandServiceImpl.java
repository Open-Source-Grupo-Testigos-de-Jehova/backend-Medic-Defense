package com.medicdefense.backend.consultation.application.internal.commandservices;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalIssue;
import com.medicdefense.backend.consultation.domain.model.commands.AskLegalIssueCommand;
import com.medicdefense.backend.consultation.domain.model.commands.CloseLegalIssue;
import com.medicdefense.backend.consultation.domain.model.commands.SendMessageCommand;
import com.medicdefense.backend.consultation.domain.services.LegalIssueCommandService;
import com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories.LegalConsultationRepository;
import com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories.LegalIssueRepository;
import org.springframework.stereotype.Service;

@Service
public class LegalIssueCommandServiceImpl implements LegalIssueCommandService {

    private final LegalIssueRepository legalIssueRepository;
    private final LegalConsultationRepository legalConsultationRepository;

    public LegalIssueCommandServiceImpl(LegalIssueRepository legalIssueRepository, LegalConsultationRepository legalConsultationRepository) {
        this.legalIssueRepository = legalIssueRepository;
        this.legalConsultationRepository = legalConsultationRepository;
    }

    @Override
    public Long handle(AskLegalIssueCommand command) {
        if(legalIssueRepository.existsByTitle(command.title())){
            throw new IllegalArgumentException("Legal Issue with same id already exists");
        }
        var legalIssue = new LegalIssue(command, legalConsultationRepository.findById(command.legalConsultationId()).orElseThrow());
        try {
            legalIssueRepository.save(legalIssue);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving legal issue: " + e.getMessage());
        }
        return legalIssue.getId();
    }

    @Override
    public void handle(SendMessageCommand command) {
        var result = legalIssueRepository.findById(command.LegalIssueId());
        if (result.isEmpty()) throw new IllegalArgumentException("Legal Issue does not exist");
        var legalIssue = result.get();
        legalIssue.addMessage(command.message(), legalIssue.getLawyerId());
        try {
            legalIssueRepository.save(legalIssue);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while sending message: " + e.getMessage());
        }
    }

    @Override
    public Long handle(CloseLegalIssue command){
        var result = legalIssueRepository.findById(command.legalIssueId());
        if (result.isEmpty()) throw new IllegalArgumentException("Legal Issue does not exist");
        var legalIssue = result.get();
        legalIssue.close();
        try {
            legalIssueRepository.save(legalIssue);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while closing legal issue: " + e.getMessage());
        }
        return legalIssue.getId();
    }
}
