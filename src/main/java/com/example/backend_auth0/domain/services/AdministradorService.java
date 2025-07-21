package com.example.backend_auth0.domain.services;

import com.example.backend_auth0.data.entities.*;
import com.example.backend_auth0.data.repository.*;
import com.example.backend_auth0.domain.dto.AdministradorDto;
import com.example.backend_auth0.domain.services.base.BaseService;
import com.example.backend_auth0.presentation.dto.request.CambiarRolRequest;
import com.example.backend_auth0.presentation.dto.request.DatosDentistaRequest;
import com.example.backend_auth0.presentation.dto.request.DatosPacienteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministradorService extends BaseService<Administrador, AdministradorDto> {

    private AdministradorRepository administradorRepository;
    private UsuarioRepository usuarioRepository;
    private DentistaRepository dentistaRepository;
    private PacienteRepository pacienteRepository;
    private EspecialidadRepository especialidadRepository;

    @Autowired
    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public List<AdministradorDto> getAll() {
        List<Administrador> administradores = administradorRepository.findAll();

        return administradores.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public AdministradorDto getById(Long id) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el administrador"));

        return mapper.toDto(administrador);
    }

    public void cambiarRol(Long usuarioId, CambiarRolRequest req) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        pacienteRepository.findByUsuarioId(usuarioId).ifPresent(pacienteRepository::delete);
        dentistaRepository.findByUsuarioId(usuarioId).ifPresent(dentistaRepository::delete);

        switch (req.getNuevoRol()) {
            case PACIENTE -> {
                DatosPacienteRequest datos = Optional.ofNullable(req.getDatosPaciente())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Faltan datos del paciente"));

                Paciente paciente = new Paciente();
                paciente.setUsuario(usuario);
                paciente.setObraSocial(datos.getObraSocial());
                paciente.setTelefonoEmergencia(datos.getTelefonoEmergencia());
                pacienteRepository.save(paciente);
            }
            case DENTISTA -> {
                DatosDentistaRequest datos = Optional.ofNullable(req.getDatosDentista())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Faltan datos del dentista"));

                Especialidad especialidad = especialidadRepository.findById(datos.getEspecialidadId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Especialidad no válida"));

                Dentista dentista = new Dentista();
                dentista.setUsuario(usuario);
                dentista.setMatricula(datos.getMatricula());
                dentista.setEspecialidad(especialidad);
                dentistaRepository.save(dentista);
            }
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol inválido");
        }
    }
}
