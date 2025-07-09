package com.example.backend_auth0.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Long    id;
    private String  auth0Id;
    private String  nombre;
    private String  apellido;
    private String  email;
    private String  telefono;
    private String  dni;
    private LocalDate fechaNacimiento;
}
