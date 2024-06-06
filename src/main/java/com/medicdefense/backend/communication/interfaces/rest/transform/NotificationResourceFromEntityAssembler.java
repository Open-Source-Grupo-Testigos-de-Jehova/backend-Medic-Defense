package com.medicdefense.backend.communication.interfaces.rest.transform;

import com.medicdefense.backend.communication.domain.model.aggregates.Notification;
import com.medicdefense.backend.communication.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity){
        return new NotificationResource(entity.getId(), entity.getMessage());
    }
}
