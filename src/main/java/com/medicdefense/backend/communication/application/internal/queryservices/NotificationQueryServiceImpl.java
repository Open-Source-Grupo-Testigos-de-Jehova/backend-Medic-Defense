package com.medicdefense.backend.communication.application.internal.queryservices;

import com.medicdefense.backend.communication.domain.model.aggregates.Notification;
import com.medicdefense.backend.communication.domain.model.queries.GetAllNotificationsQuery;
import com.medicdefense.backend.communication.domain.model.queries.GetNotificationByIdQuery;
import com.medicdefense.backend.communication.domain.services.NotificationQueryService;
import com.medicdefense.backend.communication.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

 @Service
public class NotificationQueryServiceImpl implements NotificationQueryService {
    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(GetNotificationByIdQuery query) {
        return notificationRepository.findById(query.id());
    }

    @Override
    public List<Notification> handle(GetAllNotificationsQuery query) {
        return notificationRepository.findAll();
    }
}
