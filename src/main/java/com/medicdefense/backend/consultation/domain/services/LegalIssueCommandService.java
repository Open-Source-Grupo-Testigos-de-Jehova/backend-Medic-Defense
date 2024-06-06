package com.medicdefense.backend.consultation.domain.services;

import com.medicdefense.backend.consultation.domain.model.commands.AskLegalIssueCommand;
import com.medicdefense.backend.consultation.domain.model.commands.CloseLegalIssue;
import com.medicdefense.backend.consultation.domain.model.commands.SendMessageCommand;

public interface LegalIssueCommandService {
    Long handle(AskLegalIssueCommand command);
    void handle(SendMessageCommand command);
    Long handle(CloseLegalIssue command);
}
