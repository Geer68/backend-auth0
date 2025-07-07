package com.example.backend_auth0.repository;

import com.example.backend_auth0.entities.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends BaseRepository<Usuario, Integer> {
    Optional<Usuario> findByAuth0Id(String auth0Id);
}
