package com.example.backend_auth0.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "pacientes")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Paciente extends Usuario{

    @Column(name = "obra_social")
    private String obraSocial;

    @Column(name = "telefono_emergencia")
    private String telefonoEmergencia;

}
