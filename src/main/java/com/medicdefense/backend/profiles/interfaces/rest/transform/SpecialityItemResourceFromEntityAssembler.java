package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.entities.SpecialityItems;
import com.medicdefense.backend.profiles.interfaces.rest.resources.SpecialityItemResource;

public class SpecialityItemResourceFromEntityAssembler {
    public static SpecialityItemResource toResourceFromEntity(SpecialityItems speciality) {
        return new SpecialityItemResource(
                speciality.getId(),
                speciality.getName()
        );
    }
}
