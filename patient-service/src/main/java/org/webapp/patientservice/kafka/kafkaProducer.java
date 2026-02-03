package org.webapp.patientservice.kafka;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.webapp.patientservice.model.Patient;
import patient.events.PatientEvent;

@RequiredArgsConstructor
@Service
public class kafkaProducer {
    private final KafkaTemplate<String,byte[]> kafkaTemplate;
    private final Logger log = LoggerFactory.getLogger(kafkaProducer.class);
    public void send(Patient patient) {
        PatientEvent event = PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED")
                .build();

        try {
            kafkaTemplate.send("patient", event.toByteArray());
        } catch (Exception e) {
            log.error("Error sending PatientCreated event:{}", event);
        }
    }

}
