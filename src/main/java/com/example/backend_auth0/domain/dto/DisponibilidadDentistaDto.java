package com.example.backend_auth0.domain.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class DisponibilidadDentistaDto {
    private Long id;
    private DentistaDto dentista;
    private Integer diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
