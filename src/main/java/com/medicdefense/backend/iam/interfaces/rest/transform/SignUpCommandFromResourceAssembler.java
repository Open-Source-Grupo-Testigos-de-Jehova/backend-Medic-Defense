package com.medicdefense.backend.iam.interfaces.rest.transform;

import com.medicdefense.backend.iam.domain.model.commands.SignUpCommand;
import com.medicdefense.backend.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {

    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.username(), resource.password(), resource.roles());
    }

}
