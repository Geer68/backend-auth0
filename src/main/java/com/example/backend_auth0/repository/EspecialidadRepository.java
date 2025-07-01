package com.example.backend_auth0.repository;

import com.example.backend_auth0.entities.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends BaseRepository<Especialidad, Integer> {
}
