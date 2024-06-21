package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.CreateLawyerCommand;
import com.medicdefense.backend.profiles.interfaces.rest.resources.CreateLawyerResource;

public class CreateLawyerCommandFromResourceAssembler {
    public static CreateLawyerCommand toCommandFromResource(CreateLawyerResource resource) {
        return new CreateLawyerCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.phoneNumber(),
                resource.image_url(),
                resource.DNI(),
                resource.yearsExperience(),
                resource.casesWon(),
                resource.price()
        );
    }
}
