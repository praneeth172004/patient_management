package org.webapp.patientservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private LocalDate registrationDate;

}
