package com.example.backend_auth0.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DatosCreacionCuenta {
    @NotBlank
    private String auth0Id;

    @NotBlank
    private String nombre;

    @Email
    private String email;
}
