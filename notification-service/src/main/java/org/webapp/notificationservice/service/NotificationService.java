package org.webapp.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.webapp.notificationservice.model.EmailStatus;
import org.webapp.notificationservice.model.Notification;
import org.webapp.notificationservice.repository.NotificationRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;
    private final NotificationRepository notificationRepository;

    public void sendEmail(String email, String title, String body) {

        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(body);
        notification.setEmailStatus(EmailStatus.PENDING);
        notification.setCreatedAt(LocalDateTime.now());

        notification = notificationRepository.save(notification);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(title);
            message.setText(body);
            message.setFrom("no-reply@webapp.com");

            mailSender.send(message);

            notification.setEmailStatus(EmailStatus.SENT);
            log.info("Email sent to {}", email);

        } catch (Exception e) {
            notification.setEmailStatus(EmailStatus.FAILED);
            log.error("Email sending failed", e);
        }

        notificationRepository.save(notification);
    }
}