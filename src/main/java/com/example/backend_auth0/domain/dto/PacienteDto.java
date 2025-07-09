package com.example.backend_auth0.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDto {
    private Long id;
    private UsuarioDto usuario;
    private String obraSocial;
    private String telefonoEmergencia;
}
