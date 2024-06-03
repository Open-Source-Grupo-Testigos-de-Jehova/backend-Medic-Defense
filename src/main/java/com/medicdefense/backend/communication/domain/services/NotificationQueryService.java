package com.medicdefense.backend.communication.domain.services;

import com.medicdefense.backend.communication.domain.model.aggregates.Notification;
import com.medicdefense.backend.communication.domain.model.queries.GetAllNotificationsQuery;
import com.medicdefense.backend.communication.domain.model.queries.GetNotificationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface NotificationQueryService {
    Optional<Notification> handle(GetNotificationByIdQuery query);
    List<Notification> handle(GetAllNotificationsQuery query);
}
