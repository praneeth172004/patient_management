package org.webapp.patientservice.repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webapp.patientservice.model.Patient;

import java.util.UUID;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByEmail(@NotBlank(message = "Email is Required") @Email(message = "Email should be valid") String email);

    boolean existsByEmailAndIdNot(@NotBlank(message = "Email is Required") @Email(message = "Email should be valid") String email, Long id);


}
