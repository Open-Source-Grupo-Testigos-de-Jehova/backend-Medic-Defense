package com.medicdefense.backend.iam.interfaces.rest.transform;

import com.medicdefense.backend.iam.domain.model.commands.AddANewLawyerCommand;
import com.medicdefense.backend.iam.interfaces.rest.resources.AddNewLawyerResource;

public class AddNewLawyerCommandFromResourceAssembler {
        public static AddANewLawyerCommand fromResource(AddNewLawyerResource resource) {
            return new AddANewLawyerCommand(
                    resource.firstName(),
                    resource.lastName(),
                    resource.email(),
                    resource.phoneNumber(),
                    resource.image_url(),
                    resource.DNI(),
                    resource.password(),
                    resource.yearsExperience(),
                    resource.casesWon(),
                    resource.price()
            );
        }
}
