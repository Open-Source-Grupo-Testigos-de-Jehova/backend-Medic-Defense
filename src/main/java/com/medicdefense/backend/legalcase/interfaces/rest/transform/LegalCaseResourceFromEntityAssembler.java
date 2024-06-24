// LegalCaseResourceFromEntityAssembler.java
package com.medicdefense.backend.legalcase.interfaces.rest.transform;

import com.medicdefense.backend.legalcase.domain.model.aggregates.LegalCase;
import com.medicdefense.backend.legalcase.interfaces.rest.resources.LegalCaseResource;

/**
 * This class is responsible for converting a LegalCase entity into a LegalCaseResource.
 */

public class LegalCaseResourceFromEntityAssembler {
    public static LegalCaseResource toResourceFromEntity(LegalCase entity){
        return new LegalCaseResource
                (
                        entity.getId(),
                        entity.getDescription(),
                        entity.getStatus().name(),
                        entity.getLawyerRecordId(),
                        entity.getMedicRecordId()
                );
    }
}