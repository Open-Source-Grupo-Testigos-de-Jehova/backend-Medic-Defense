package com.medicdefense.backend.consultation.interfaces.rest.transform;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.interfaces.rest.resources.LegalConsultationResource;

public class LegalConsultationResourceFromEntityAssembler {
    public static LegalConsultationResource toResourceFromEntity(LegalConsultation entity) {
        return new LegalConsultationResource(entity.getMedicDefenseLegalConsultationRecordId());
    }
}
