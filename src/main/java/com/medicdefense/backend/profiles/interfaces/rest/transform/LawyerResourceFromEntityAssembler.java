package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.aggregate.Lawyer;
import com.medicdefense.backend.profiles.interfaces.rest.resources.LawyerResource;

public class LawyerResourceFromEntityAssembler {
    public static LawyerResource toResourceFromEntity(Lawyer entity) {
        return new LawyerResource(
                entity.getLawyerRecordId(),
                entity.getProfileId(),
                entity.getYearsExperience(),
                entity.getCasesWon(),
                entity.getPrice()
        );
    }
}
