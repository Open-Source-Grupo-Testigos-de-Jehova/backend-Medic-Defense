package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.UpdateNameCommand;
import com.medicdefense.backend.profiles.interfaces.rest.resources.UpdateNameResource;

public class UpdateNameCommandFromResourceAssembler {
    public static UpdateNameCommand ToCommandFromResource(Long profileId, UpdateNameResource resource) {
        return new UpdateNameCommand(resource.firstName(), resource.lastName(), profileId);
    }
}
