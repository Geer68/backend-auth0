package com.example.backend_auth0.dto;

import lombok.Data;

@Data
public class DentistaDto {
    private Long dentistaId;
    private UsuarioDto usuario;
    private EspecialidadDto especialidad;
    private String matricula;
}
