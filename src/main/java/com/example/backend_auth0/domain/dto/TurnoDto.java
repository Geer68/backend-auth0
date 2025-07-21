package com.example.backend_auth0.domain.dto;

import com.example.backend_auth0.data.enums.EstadoTurno;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TurnoDto {
    private Long id;
    private PacienteDto paciente;
    private DentistaDto dentista;

    private OffsetDateTime fechaHora;
    private OffsetDateTime creadoEn;
    private EstadoTurno estado;

    private String notasTratamiento;
    private String comentarios;
}
