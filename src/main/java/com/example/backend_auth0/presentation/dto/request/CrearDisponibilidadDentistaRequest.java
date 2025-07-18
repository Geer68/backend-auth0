package com.example.backend_auth0.presentation.dto.request;

import com.example.backend_auth0.domain.dto.DentistaDto;
import lombok.Data;

import java.time.LocalTime;

@Data
public class CrearDisponibilidadDentistaRequest {
    private Long id;
    private Long dentistaId;
    private Integer diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
