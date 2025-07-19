package com.example.backend_auth0.domain.dto;

import com.example.backend_auth0.data.entities.Dentista;
import com.example.backend_auth0.data.entities.Paciente;
import com.example.backend_auth0.data.enums.EstadoTurno;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TurnoDto {
    private PacienteDto paciente;
    private DentistaDto dentista;

    private OffsetDateTime fechaHora;
    private OffsetDateTime creadoEn;
    private EstadoTurno estado;

    private String notasTratamiento;
    private String comentarios;
}
