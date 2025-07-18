package com.example.backend_auth0.data.repository;

import com.example.backend_auth0.data.entities.Especialidad;
import com.example.backend_auth0.data.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspecialidadRepository extends BaseRepository<Especialidad, Long> {
    Optional<Especialidad> findByNombre(String nombre);
}
