package com.example.backend_auth0.data.repository;

import com.example.backend_auth0.data.entities.DisponibilidadDentista;
import com.example.backend_auth0.data.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisponibilidadDentistaRepository extends BaseRepository<DisponibilidadDentista, Long> {

    List<DisponibilidadDentista> findAllByDentistaUsuarioDeletedAtIsNullAndDeletedAtIsNull();
    List<DisponibilidadDentista>  findByDentistaIdAndDentistaUsuarioDeletedAtIsNull(Long dentistaId);

}
