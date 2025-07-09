package com.example.backend_auth0.data.repository;

import com.example.backend_auth0.data.entities.Usuario;
import com.example.backend_auth0.data.repository.base.BaseRepository;
import java.util.Optional;

public interface UsuarioRepository extends BaseRepository<Usuario, Integer> {
    Optional<Usuario> findByAuth0Id(String auth0Id);
}
