package com.medicdefense.backend.communication.interfaces.rest.transform;

import com.medicdefense.backend.communication.domain.model.commands.CreateNotificationCommand;
import com.medicdefense.backend.communication.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(resource.message());
    }
}
