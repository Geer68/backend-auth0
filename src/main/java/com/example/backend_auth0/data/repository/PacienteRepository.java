package com.example.backend_auth0.data.repository;

import java.util.Optional;

import com.example.backend_auth0.data.entities.Paciente;
import com.example.backend_auth0.data.entities.Usuario;
import com.example.backend_auth0.data.repository.base.BaseRepository;

public interface PacienteRepository extends BaseRepository<Paciente, Integer> {

    Optional<Paciente> findByUsuario(Usuario usuario);
}
