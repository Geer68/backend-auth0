package com.example.backend_auth0.presentation.dto.request;

import com.example.backend_auth0.domain.dto.UsuarioDto;
import lombok.Data;

@Data
public class CrearPacienteRequest {
    private Long usuarioId;
    private String obraSocial;
    private String telefonoEmergencia;
}
