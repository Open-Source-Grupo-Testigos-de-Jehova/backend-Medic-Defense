package com.medicdefense.backend.resources.interfaces.rest.transform;

import com.medicdefense.backend.resources.domain.model.aggregates.EducationalResource;
import com.medicdefense.backend.resources.interfaces.rest.resources.EducationalResourceResource;

public class EducationalResourceResourceFromEntityAssembler {
    public static EducationalResourceResource toResourceFromEntity(EducationalResource entity){
        return new EducationalResourceResource
                (
                        entity.getId(),
                        entity.getTitle(),
                        entity.getAuthor(),
                        entity.getContentType().name(),
                        entity.getUrl());
    }
}
