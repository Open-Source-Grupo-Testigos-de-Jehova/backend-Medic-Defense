package com.medicdefense.backend.resources.interfaces.rest.transform;

import com.medicdefense.backend.resources.domain.model.commands.CreateEducationalResourceCommand;
import com.medicdefense.backend.resources.interfaces.rest.resources.CreateEducationalResourceResource;

public class CreateEducationalResourceCommandFromResourceAssembler {
    public static CreateEducationalResourceCommand toCommandFromResource(CreateEducationalResourceResource resource){
        return new CreateEducationalResourceCommand
                (
                        resource.title(),
                        resource.author(),
                        resource.contentType(),
                        resource.url());
    }
}
