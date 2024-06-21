package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.AddSpecialityCommand;
import com.medicdefense.backend.profiles.interfaces.rest.resources.AddSpecialityResource;

public class AddSpecialityCommandFromResource {
    public static AddSpecialityCommand fromResource(AddSpecialityResource resource) {
        return new AddSpecialityCommand(
                resource.specialityName(),
                resource.ProfileId()
        );
    }
}
