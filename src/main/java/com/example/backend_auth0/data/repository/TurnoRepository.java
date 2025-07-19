package com.example.backend_auth0.data.repository;

import com.example.backend_auth0.data.entities.Turno;
import com.example.backend_auth0.data.repository.base.BaseRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface TurnoRepository extends BaseRepository<Turno, Long> {
    List<Turno> findByDentistaId(Long dentistaId);
    List<Turno> findByPacienteId(Long pacienteId);
    boolean existsByDentistaIdAndPacienteIdAndFechaHora(Long dentistaId, Long pacienteId, OffsetDateTime fechaHora);

}
