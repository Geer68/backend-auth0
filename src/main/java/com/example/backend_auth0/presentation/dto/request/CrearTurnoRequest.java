package com.example.backend_auth0.presentation.dto.request;

import com.example.backend_auth0.data.enums.EstadoTurno;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CrearTurnoRequest {
    @NotNull
    private Long pacienteId;

    @NotNull
    private Long dentistaId;

    @NotNull
    private OffsetDateTime fechaHora;

    private EstadoTurno estado;
}
