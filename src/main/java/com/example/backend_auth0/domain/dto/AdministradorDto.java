package com.example.backend_auth0.domain.dto;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministradorDto {
    private Long id;
    private UsuarioDto usuario;
    private OffsetDateTime fechaIngreso;
}
