package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.CreateMedicCommand;
import com.medicdefense.backend.profiles.interfaces.rest.resources.CreateMedicResource;

public class CreateMedicCommandFromResourceAssembler {
    public static CreateMedicCommand toCommandFromResource(CreateMedicResource resource) {
        return new CreateMedicCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.phoneNumber(),
                resource.image_url(),
                resource.DNI()
        );
    }
}
