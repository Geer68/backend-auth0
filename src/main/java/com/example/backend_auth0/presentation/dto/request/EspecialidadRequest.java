package com.example.backend_auth0.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EspecialidadRequest {
    @NotNull
    private String nombre;
}
