package org.webapp.notificationservice.dto;

import lombok.Data;
import org.webapp.notificationservice.model.EmailStatus;
import org.webapp.notificationservice.model.NotificationType;

import java.time.LocalDateTime;
@Data
public class NotificationResponse {
    private Long id;
    private String title;
    private String message;
    private NotificationType type;
    private Long userId;
    private EmailStatus emailStatus;
    private LocalDateTime createdAt;
}
