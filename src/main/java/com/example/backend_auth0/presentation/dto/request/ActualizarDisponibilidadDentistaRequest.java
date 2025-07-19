package com.example.backend_auth0.presentation.dto.request;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ActualizarDisponibilidadDentistaRequest {
    private Integer diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
