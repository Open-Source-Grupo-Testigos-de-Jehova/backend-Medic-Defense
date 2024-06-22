package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.UpdatePhoneNumberCommand;
import com.medicdefense.backend.profiles.interfaces.rest.resources.UpdatePhoneResource;

public class UpdatePhoneCommandFromResourceAssembler {
    public static UpdatePhoneNumberCommand ToCommandFromResource(Long profileId, UpdatePhoneResource resource) {
        return new UpdatePhoneNumberCommand(resource.phone(), profileId);
    }
}
