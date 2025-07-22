package com.example.backend_auth0.data.repository;

import java.util.List;
import java.util.Optional;

import com.example.backend_auth0.data.entities.Paciente;
import com.example.backend_auth0.data.entities.Usuario;
import com.example.backend_auth0.data.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends BaseRepository<Paciente, Long> {
    Optional<Paciente> findByUsuario_Auth0Id(String auth0Id);
    Optional<Paciente> findByUsuario(Usuario usuario);
    Optional<Paciente> findByUsuarioId(Long usuarioId);

    @Query("SELECT p FROM Paciente p WHERE p.usuario.email = :dni AND p.usuario.deletedAt IS NULL")
    Optional<Paciente> findByUsuarioEmailAndDeletedAtIsNull(@Param("email") String email);

    @Query("SELECT p FROM Paciente p WHERE p.usuario.dni = :dni AND p.usuario.deletedAt IS NULL")
    Optional<Paciente> findByUsuarioDniAndDeletedAtIsNull(@Param("dni") String dni);
}
