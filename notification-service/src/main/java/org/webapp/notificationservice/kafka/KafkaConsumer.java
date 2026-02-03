package org.webapp.notificationservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;
import org.webapp.notificationservice.service.NotificationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "patient", groupId = "notification-service")
    public void consumeEvent(byte[] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);

            log.info("Received patient event for email={}", patientEvent.getEmail());

            notificationService.sendEmail(
                    patientEvent.getEmail(),
                    "Patient Created",
                    "Your patient profile has been created successfully"
            );

        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing Kafka event", e);
        }
    }
}