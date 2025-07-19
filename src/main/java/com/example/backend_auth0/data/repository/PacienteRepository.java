package com.example.backend_auth0.data.repository;

import java.util.Optional;

import com.example.backend_auth0.data.entities.Paciente;
import com.example.backend_auth0.data.entities.Usuario;
import com.example.backend_auth0.data.repository.base.BaseRepository;

public interface PacienteRepository extends BaseRepository<Paciente, Long> {
    Optional<Paciente> findByAuth0Id(String auth0Id);
    Optional<Paciente> findByUsuario(Usuario usuario);
    Optional<Paciente> findByUsuarioEmailAndDeletedAtIsNull(String email);
    Optional<Paciente> findByUsuarioDniAndDeletedAtIsNull(String dni);
}
