package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.CreateUniversityCommand;
import com.medicdefense.backend.profiles.interfaces.rest.resources.CreateUniversityResource;

public class CreateUniversityCommandFromResourceAssembler {
    public static CreateUniversityCommand toCommandFromResource(CreateUniversityResource resource) {
        return new CreateUniversityCommand(
                resource.name()
        );
    }
}
