package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.aggregate.MedicStudent;
import com.medicdefense.backend.profiles.interfaces.rest.resources.MedicStudentResource;

public class MedicStudentResourceFromEntityAssembler {
    public static MedicStudentResource toResourceFromEntity(MedicStudent entity) {
        return new MedicStudentResource(
                entity.getMedicStudentRecordId(),
                entity.getProfileId(),
                entity.getUniversity().getId(),
                entity.getConsultationsMade(),
                entity.getPaidServices()
        );
    }
}
