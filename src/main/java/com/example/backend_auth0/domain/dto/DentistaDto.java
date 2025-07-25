package com.example.backend_auth0.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DentistaDto {
    private Long id;
    private UsuarioDto      usuario;
    private EspecialidadDto especialidad;
    private String          matricula;
}
