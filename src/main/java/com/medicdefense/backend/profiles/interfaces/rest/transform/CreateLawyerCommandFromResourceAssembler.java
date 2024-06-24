package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.CreateLawyerCommand;
import com.medicdefense.backend.profiles.interfaces.rest.resources.CreateLawyerResource;

public class CreateLawyerCommandFromResourceAssembler {
    public static CreateLawyerCommand toCommandFromResource(CreateLawyerResource resource) {
        return new CreateLawyerCommand(
                resource.userName(),
                resource.password(),
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.phoneNumber(),
                resource.DNI(),
                resource.image_url(),
                resource.yearsExperience(),
                resource.casesWon(),
                resource.price()
        );
    }
}
