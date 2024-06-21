package com.medicdefense.backend.consultation.interfaces.rest.transform;

import com.medicdefense.backend.consultation.domain.model.commands.CreateLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.valueobjects.MedicDefenseId;
import com.medicdefense.backend.consultation.interfaces.rest.resources.CreateLegalConsultationResource;

public class LegalCreateConsultationCommandFromResourceAssembler {
    public static CreateLegalConsultationCommand toCommandFromResource(CreateLegalConsultationResource resource) {
        return new CreateLegalConsultationCommand(new MedicDefenseId(resource.medicId()), new MedicDefenseId(resource.lawyerId()));
    }
}
