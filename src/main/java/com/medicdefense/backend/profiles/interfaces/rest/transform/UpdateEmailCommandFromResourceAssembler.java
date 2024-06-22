package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.UpdateEmailCommand;
import com.medicdefense.backend.profiles.interfaces.rest.resources.UpdateEmailResource;

public class UpdateEmailCommandFromResourceAssembler {
    public static UpdateEmailCommand ToCommandFromResource(Long profileId, UpdateEmailResource resource) {
        return new UpdateEmailCommand(resource.email(), profileId);
    }
}
