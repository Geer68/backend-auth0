package com.example.backend_auth0.data.repository;

import com.example.backend_auth0.data.entities.Dentista;
import com.example.backend_auth0.data.entities.Usuario;
import com.example.backend_auth0.data.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DentistaRepository extends BaseRepository<Dentista, Long> {
    Optional<Dentista> findByUsuario(Usuario usuario);
    Optional<Dentista> findByUsuarioId(Long usuarioId);
    List<Dentista>    findAllByUsuarioDeletedAtIsNull();
    Optional<Dentista> findByIdAndUsuarioDeletedAtIsNull(Long id);
}
