package com.medicdefense.backend.consultation.interfaces.rest.transform;

import com.medicdefense.backend.consultation.domain.model.commands.AskLegalIssueCommand;
import com.medicdefense.backend.consultation.interfaces.rest.resources.AskLegalIssueResource;

public class AskLegalIssueCommandFromResourceAssembler {
    public static AskLegalIssueCommand toCommandFromResource(AskLegalIssueResource resource) {
        return new AskLegalIssueCommand(
                resource.title(),
                resource.consultationId(),
                resource.message()
        );
    }
}
