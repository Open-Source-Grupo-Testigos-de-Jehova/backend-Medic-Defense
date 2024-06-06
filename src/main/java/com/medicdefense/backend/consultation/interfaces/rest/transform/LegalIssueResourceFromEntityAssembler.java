package com.medicdefense.backend.consultation.interfaces.rest.transform;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalIssue;
import com.medicdefense.backend.consultation.interfaces.rest.resources.LegalIssueResource;

public class LegalIssueResourceFromEntityAssembler {
    public static LegalIssueResource toResourceFromEntity(LegalIssue entity) {
        return new LegalIssueResource(
                entity.getId(),
                entity.getTitle(),
                entity.getFirstMessage(),
                entity.getLegalConsultationId(),
                entity.getStatus()
        );
    }
}
