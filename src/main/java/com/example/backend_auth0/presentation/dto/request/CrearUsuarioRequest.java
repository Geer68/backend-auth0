package com.example.backend_auth0.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CrearUsuarioRequest {
    @NotBlank
    private String auth0Id;

    @NotBlank
    private String nombre;

    @Email
    private String email;
}
