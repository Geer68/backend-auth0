package com.example.backend_auth0.presentation.dto.request;

import com.example.backend_auth0.data.enums.Rol;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CambiarRolRequest {
    @NotNull
    private Rol nuevoRol;

    private DatosPacienteRequest datosPaciente;
    private DatosDentistaRequest datosDentista;
}
