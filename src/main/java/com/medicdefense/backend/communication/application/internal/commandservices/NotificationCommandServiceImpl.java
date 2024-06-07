package com.medicdefense.backend.communication.application.internal.commandservices;

import com.medicdefense.backend.communication.domain.model.aggregates.Notification;
import com.medicdefense.backend.communication.domain.model.commands.CreateNotificationCommand;
import com.medicdefense.backend.communication.domain.services.NotificationCommandService;
import com.medicdefense.backend.communication.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {
    private final NotificationRepository notificationRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(CreateNotificationCommand command) {
        //return Optional.empty();
        var notification = new Notification(command);
        var createdNotification = notificationRepository.save(notification);
        return Optional.of(createdNotification);
    }
}
