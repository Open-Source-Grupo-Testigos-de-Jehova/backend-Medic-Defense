// CreateLegalCaseCommandFromResourceAssembler.java
package com.medicdefense.backend.legalcase.interfaces.rest.transform;

import com.medicdefense.backend.legalcase.domain.model.commands.CreateLegalCaseCommand;
import com.medicdefense.backend.legalcase.interfaces.rest.resources.CreateLegalCaseResource;


/**
 * This class is responsible for converting a CreateLegalCaseResource into a CreateLegalCaseCommand.
 */

public class CreateLegalCaseCommandFromResourceAssembler {
    public static CreateLegalCaseCommand toCommandFromResource(CreateLegalCaseResource resource){
        return new CreateLegalCaseCommand
                (
                        resource.description(),
                        resource.lawyerId(),
                        resource.clientId()
                );
    }
}