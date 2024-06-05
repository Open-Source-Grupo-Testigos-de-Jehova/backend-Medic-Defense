package com.medicdefense.backend.consultation.application.internal.commandservices;

import com.medicdefense.backend.consultation.domain.model.commands.AskLegalIssueCommand;
import com.medicdefense.backend.consultation.domain.model.commands.CloseLegalIssue;
import com.medicdefense.backend.consultation.domain.model.commands.SendMessageCommand;
import com.medicdefense.backend.consultation.domain.services.LegalIssueCommandService;
import com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories.LegalIssueRepository;
import org.springframework.stereotype.Service;

@Service
public class LegalIssueCommandServiceImpl implements LegalIssueCommandService {

    private final LegalIssueRepository legalIssueRepository;

    public LegalIssueCommandServiceImpl(LegalIssueRepository legalIssueRepository) {
        this.legalIssueRepository = legalIssueRepository;
    }

    @Override
    public Long handle(AskLegalIssueCommand command) {
        if(legalIssueRepository.existsByTitle(command.title())){
            throw new IllegalArgumentException("Legal Issue with same id already exists");
        }
    }

    @Override
    public void handle(SendMessageCommand command) {

    }

    @Override
    public Long handle(CloseLegalIssue command) {
        return 0;
    }
}
