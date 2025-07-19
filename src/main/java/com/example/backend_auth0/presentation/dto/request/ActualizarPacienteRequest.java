package com.example.backend_auth0.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ActualizarPacienteRequest {
    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @Email
    private String email;

    private String telefono;

    private String dni;

    private LocalDate fechaNacimiento;

    private String obraSocial;

    private String telefonoEmergencia;
}