package com.example.backend_auth0.data.repository;

import com.example.backend_auth0.data.entities.DisponibilidadDentista;
import com.example.backend_auth0.data.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisponibilidadDentistaRepository extends BaseRepository<DisponibilidadDentista, Long> {

    @Query("SELECT d FROM DisponibilidadDentista d WHERE d.dentista.deletedAt IS NULL AND d.deletedAt IS NULL")
    List<DisponibilidadDentista> findAll();

    List<DisponibilidadDentista> findByDentistaIdAndDentistaDeletedAtIsNull(Long dentistaId);
}
