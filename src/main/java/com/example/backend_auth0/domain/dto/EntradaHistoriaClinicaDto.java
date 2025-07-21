package com.example.backend_auth0.domain.dto;

import com.example.backend_auth0.data.enums.EstadoTurno;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaHistoriaClinicaDto {
    private OffsetDateTime fechaHora;
    private String dentistaNombre;
    private String notasTratamiento;
    private String comentarios;
}
