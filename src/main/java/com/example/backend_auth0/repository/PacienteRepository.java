package com.example.backend_auth0.repository;

import com.example.backend_auth0.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends BaseRepository<Paciente, Integer> {
}
