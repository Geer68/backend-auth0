package com.example.backend_auth0.dto;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class UsuarioDto {
    private Long    usuarioId;
    private String  auth0Id;
    private String  nombre;
    private String  apellido;
    private String  email;
    private String  telefono;
    private String  dni;
    private LocalDate fechaNacimiento;
    private Instant creadoEn;
    private Instant actualizadoEn;
}
