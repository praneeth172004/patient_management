package org.webapp.patientservice.Mapper;

import org.webapp.patientservice.dto.PatientRequestDTO;
import org.webapp.patientservice.dto.PatientResponseDTO;
import org.webapp.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDto(Patient p) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(p.getId().toString());
        dto.setName(p.getName());
        dto.setDateOfBirth(p.getDateOfBirth().toString());
        dto.setAddress(p.getAddress());
        dto.setEmail(p.getEmail());
        return dto;
    }
    public static Patient toEntity(PatientRequestDTO dto) {
        Patient p = new Patient();
        p.setAddress(dto.getAddress());
        p.setEmail(dto.getEmail());
        p.setName(dto.getName());
        p.setRegistrationDate(LocalDate.parse(dto.getRegistrationDate()));
        p.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        return p;
    }
}
