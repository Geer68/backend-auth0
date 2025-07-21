package com.example.backend_auth0.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ActualizarDentistaRequest {

    private String nombre;

    private String apellido;

    @Email
    private String email;

    @Size(max = 20)
    private String telefono;

    @Size(max = 20)
    private String dni;

    private String matricula;

    private LocalDate fechaNacimiento;

    private Long especialidadId;

}
