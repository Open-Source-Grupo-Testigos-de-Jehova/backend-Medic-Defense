package com.medicdefense.backend.consultation.interfaces.rest.transform;

import com.medicdefense.backend.consultation.domain.model.entities.LegalIssueItem;
import com.medicdefense.backend.consultation.interfaces.rest.resources.LegalIssueResource;

public class LegalIssueResourceFromEntityAssembler {
    public static LegalIssueResource toResourceFromEntity(LegalIssueItem entity) {
        return new LegalIssueResource(
                entity.getId(),
                entity.getConsultation(),
                entity.getResponse(),
                entity.getLegalConsultationId(),
                entity.getStatus()
        );
    }
}
