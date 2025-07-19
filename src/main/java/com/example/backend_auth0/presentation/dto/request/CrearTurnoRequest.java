package com.example.backend_auth0.presentation.dto.request;

import com.example.backend_auth0.data.enums.EstadoTurno;
import com.example.backend_auth0.domain.dto.DentistaDto;
import com.example.backend_auth0.domain.dto.PacienteDto;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CrearTurnoRequest {
    private Long pacienteId;
    private Long dentistaId;

    private OffsetDateTime fechaHora;
}
