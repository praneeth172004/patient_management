package org.webapp.patientservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.webapp.patientservice.dto.PatientRequestDTO;
import org.webapp.patientservice.dto.PatientResponseDTO;
import org.webapp.patientservice.dto.validators.CreatePatientValidationGroup;
import org.webapp.patientservice.repository.PatientRepository;
import org.webapp.patientservice.service.PatientService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
@Tag(name="Patient",description="API for managing Patients")
public class PatientController {
    public final PatientService patientService;
    private final PatientRepository patientRepository;

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patientResponseDTOList=patientService.findAllPatients();
        return ResponseEntity.ok().body(patientResponseDTOList);
    }

    @PostMapping("/add")
    @Operation(summary = "Create a new Patient")
    public ResponseEntity<PatientResponseDTO> addPatient(@RequestBody @Validated({Default.class, CreatePatientValidationGroup.class}) PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO=patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a new Patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id,@Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO=patientService.updatePatient(id,patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        String response=patientService.deletePatient(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}/email")
    @Operation(summary = "Email of  a Patient")
    public ResponseEntity<String> getEmail(@PathVariable Long id) {
        String email= patientService.getPatientEmailById(id);
        return ResponseEntity.ok().body(email);
    }
}
