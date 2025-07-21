package com.example.backend_auth0.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisponibilidadDentistaDto {
    private Long id;
    private DentistaDto dentista;
    private Integer diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
