package org.webapp.patientservice.service;

import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import org.webapp.patientservice.Mapper.PatientMapper;
import org.webapp.patientservice.dto.PatientRequestDTO;
import org.webapp.patientservice.dto.PatientResponseDTO;
import org.webapp.patientservice.exception.EmailAlreadyExistsException;
import org.webapp.patientservice.exception.PatientNotFoundException;
import org.webapp.patientservice.grpc.BillingServiceGrpcClient;
import org.webapp.patientservice.model.Patient;
import org.webapp.patientservice.repository.PatientRepository;
import org.webapp.patientservice.kafka.kafkaProducer;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
//@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final kafkaProducer kafkaProducer;

    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient,kafkaProducer kafkaProducer) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    public List<PatientResponseDTO> findAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDto).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A Patient with this email already exists "+patientRequestDTO.getEmail());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toEntity(patientRequestDTO));
        billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(),
                newPatient.getName(), newPatient.getEmail());
        kafkaProducer.send(newPatient);
        return PatientMapper.toDto(newPatient);
    }

    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO dto) {
        Patient patient=patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient with id "+id+" not found"));
        if(patientRepository.existsByEmailAndIdNot(dto.getEmail(),id)){
            throw new EmailAlreadyExistsException("A Patient with this email already exists "+dto.getEmail());
        }
        if (dto.getName() != null && !dto.getName().isBlank()) {
            patient.setName(dto.getName());
        }

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            patient.setEmail(dto.getEmail());
        }

        if (dto.getAddress() != null && !dto.getAddress().isBlank()) {
            patient.setAddress(dto.getAddress());
        }

        if (dto.getDateOfBirth() != null && !dto.getDateOfBirth().isBlank()) {
            patient.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        }
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDto(updatedPatient);
    }

    public String deletePatient(Long id) {
        Patient patient=patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient with id "+id+" not found"));
        patientRepository.deleteById(id);
        return "Patient with id " + id + " deleted successfully";
    }

    public String getPatientEmailById(Long id) {
        return patientRepository.findById(id)
                .map(Patient::getEmail)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}
