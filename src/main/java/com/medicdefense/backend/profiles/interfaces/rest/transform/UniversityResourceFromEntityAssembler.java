package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.entities.University;
import com.medicdefense.backend.profiles.interfaces.rest.resources.UniversityResource;

public record UniversityResourceFromEntityAssembler() {
    public static UniversityResource toResourceFromEntity(University university) {
        return new UniversityResource(
                university.getId(),
                university.getUniversityName()
        );
    }
}
