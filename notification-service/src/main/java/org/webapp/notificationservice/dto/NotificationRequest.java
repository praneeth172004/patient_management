package org.webapp.notificationservice.dto;

import lombok.Data;
import org.webapp.notificationservice.model.NotificationType;

@Data
public class NotificationRequest {
    private String userId;
    private String message;
    private String title;
    private NotificationType type;

}
