package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.CreateMedicStudentCommand;
import com.medicdefense.backend.profiles.interfaces.rest.resources.CreateMedicStudentResource;

public class CreateMedicStudentCommandFromResourceAssembler {
    public static CreateMedicStudentCommand toCommandFromResource(CreateMedicStudentResource resource) {
        return new CreateMedicStudentCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.phoneNumber(),
                resource.image_url(),
                resource.DNI(),
                resource.universityName()
        );
    }
}
