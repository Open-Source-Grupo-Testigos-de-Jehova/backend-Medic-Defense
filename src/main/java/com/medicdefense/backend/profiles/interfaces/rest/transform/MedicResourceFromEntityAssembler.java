package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.aggregate.Medic;
import com.medicdefense.backend.profiles.interfaces.rest.resources.MedicResource;

public class MedicResourceFromEntityAssembler {
    public static MedicResource toResourceFromEntity(Medic entity) {
        return new MedicResource(
                entity.getMedicRecordId(),
                entity.getProfileId(),
                entity.getUserId(),
                entity.getConsultationsMade(),
                entity.getPaidServices()
        );
    }
}
