package com.medicdefense.backend.consultation.interfaces.rest.transform;

import com.medicdefense.backend.consultation.domain.model.commands.UpdateLegalConsultationCommand;
import com.medicdefense.backend.consultation.interfaces.rest.resources.UpdateLegalConsultationResource;

public class UpdateLegalConsultationCommandFromResourceAssembler {
    public static UpdateLegalConsultationCommand toCommandFromResource(Long consultationId, UpdateLegalConsultationResource resource) {
        return new UpdateLegalConsultationCommand(
                consultationId,
                resource.date()
        );
    }
}
