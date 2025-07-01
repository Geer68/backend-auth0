package com.example.backend_auth0.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "especialidades")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Especialidad extends BaseEntity {

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

}
