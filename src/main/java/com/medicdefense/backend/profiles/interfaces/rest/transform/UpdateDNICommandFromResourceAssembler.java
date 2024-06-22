package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.UpdateDniCommand;
import com.medicdefense.backend.profiles.interfaces.rest.resources.UpdateDNIResource;

public class UpdateDNICommandFromResourceAssembler {
    public static UpdateDniCommand ToCommandFromResource(Long profileId, UpdateDNIResource resource) {
        return new UpdateDniCommand(resource.Dni(), profileId);
    }
}
