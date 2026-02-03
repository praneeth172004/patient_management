package org.webapp.notificationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String userId;
    private String title;

    private NotificationType type;

    @Enumerated(EnumType.STRING)
    private EmailStatus EmailStatus;

    private LocalDateTime createdAt;
    private LocalDateTime sentAt;

}
