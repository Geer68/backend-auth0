package com.example.backend_auth0.data.repository;

import com.example.backend_auth0.data.entities.Administrador;
import com.example.backend_auth0.data.entities.Usuario;
import com.example.backend_auth0.data.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends BaseRepository<Administrador, Integer> {
    Optional<Administrador> findByUsuario(Usuario usuario);
}
