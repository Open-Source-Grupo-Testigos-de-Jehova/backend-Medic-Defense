package com.medicdefense.backend.communication.domain.services;

import com.medicdefense.backend.communication.domain.model.aggregates.Notification;
import com.medicdefense.backend.communication.domain.model.commands.CreateNotificationCommand;

import java.util.Optional;

public interface NotificationCommandService {
    Optional<Notification> handle(CreateNotificationCommand command);
}
