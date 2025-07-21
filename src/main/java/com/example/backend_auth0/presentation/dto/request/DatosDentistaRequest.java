package com.example.backend_auth0.presentation.dto.request;

import lombok.Data;

@Data
public class DatosDentistaRequest {
    private String matricula;
    private Long especialidadId;
}
