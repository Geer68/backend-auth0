package com.example.backend_auth0.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "especialidades")
@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Especialidad implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

}
