package com.example.backend_auth0.entities;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
<<<<<<<< HEAD:src/main/java/com/example/backend_auth0/entities/Especialidad.java
public class Especialidad implements Serializable {
========
@Data
public abstract class BaseEntity implements Serializable {
>>>>>>>> 7346d4ab56960e7a8b0455a6b69e74b42fcae501:src/main/java/com/example/backend_auth0/entities/BaseEntity.java

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean deleted = false;
}
