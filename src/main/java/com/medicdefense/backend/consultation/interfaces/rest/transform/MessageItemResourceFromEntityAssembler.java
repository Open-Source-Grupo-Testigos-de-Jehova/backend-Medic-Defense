package com.medicdefense.backend.consultation.interfaces.rest.transform;

import com.medicdefense.backend.consultation.domain.model.entities.MessageItem;
import com.medicdefense.backend.consultation.interfaces.rest.resources.MessageItemResource;

public class MessageItemResourceFromEntityAssembler {
    public static MessageItemResource toResourceFromEntity(MessageItem entity) {
        return new MessageItemResource(
                entity.getId(),
                entity.getMessage(),
                entity.getSenderId(),
                entity.getLegalIssue().getId()
        );
    }
}
