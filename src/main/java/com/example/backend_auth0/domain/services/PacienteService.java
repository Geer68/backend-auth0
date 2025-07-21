package com.example.backend_auth0.domain.services;

import com.example.backend_auth0.data.entities.Paciente;
import com.example.backend_auth0.data.entities.Turno;
import com.example.backend_auth0.data.entities.Usuario;
import com.example.backend_auth0.data.mapper.PacienteMapper;
import com.example.backend_auth0.data.repository.PacienteRepository;
import com.example.backend_auth0.data.repository.TurnoRepository;
import com.example.backend_auth0.data.repository.UsuarioRepository;
import com.example.backend_auth0.domain.dto.EntradaHistoriaClinicaDto;
import com.example.backend_auth0.domain.dto.PacienteDto;
import com.example.backend_auth0.domain.services.base.BaseService;
import com.example.backend_auth0.presentation.dto.request.ActualizarPacienteRequest;
import com.example.backend_auth0.presentation.dto.request.CrearPacienteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService extends BaseService<Paciente, PacienteDto> {
    private final TurnoRepository turnoRepository;
    private PacienteRepository pacienteRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, UsuarioRepository usuarioRepository, TurnoRepository turnoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.turnoRepository = turnoRepository;
    }

    public List<PacienteDto> getAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();

        return pacientes.stream()
                .map(mapper::toDto)
                .toList();
    }

    public PacienteDto getById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
        return mapper.toDto(paciente);
    }

    public PacienteDto getByAuth0Id(String auth0Id) {
        Paciente paciente = pacienteRepository.findByUsuario_Auth0Id(auth0Id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
        return mapper.toDto(paciente);
    }

    public PacienteDto create(CrearPacienteRequest pacienteNuevo) {
        Usuario usuario = usuarioRepository.findById(pacienteNuevo.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Paciente paciente = new Paciente();
        paciente.setObraSocial(pacienteNuevo.getObraSocial());
        paciente.setTelefonoEmergencia(pacienteNuevo.getTelefonoEmergencia());
        paciente.setObraSocial(pacienteNuevo.getObraSocial());
        paciente.setUsuario(usuario);

        pacienteRepository.save(paciente);

        return mapper.toDto(paciente);
    }

    public PacienteDto buscarPorEmailODni(String email, String dni) {
        Optional<Paciente> pacienteOpt;

        if (email != null && !email.isBlank()) {
            pacienteOpt = pacienteRepository.findByUsuarioEmailAndDeletedAtIsNull(email);
        } else if (dni != null && !dni.isBlank()) {
            pacienteOpt = pacienteRepository.findByUsuarioDniAndDeletedAtIsNull(dni);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debes proporcionar un email o DNI.");
        }

        Paciente paciente = pacienteOpt.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado.")
        );

        return mapper.toDto(paciente);
    }

    public PacienteDto update(Long pacienteId, ActualizarPacienteRequest req) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));

        Usuario usuario = paciente.getUsuario();

        usuario.setNombre(req.getNombre());
        usuario.setApellido(req.getApellido());
        usuario.setEmail(req.getEmail());
        usuario.setTelefono(req.getTelefono());
        usuario.setDni(req.getDni());
        usuario.setFechaNacimiento(req.getFechaNacimiento());

        paciente.setObraSocial(req.getObraSocial());
        paciente.setTelefonoEmergencia(req.getTelefonoEmergencia());

        return mapper.toDto(paciente);
    }

    public List<EntradaHistoriaClinicaDto> getHistoriaClinica(Long pacienteId){
        List<Turno> turnos = turnoRepository.findByPacienteIdOrderByFechaHoraDesc(pacienteId);

        if(turnos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El paciente no tiene turnos");
        }

        return turnos.stream().map(turno -> new EntradaHistoriaClinicaDto(
                turno.getFechaHora(),
                turno.getDentista().getUsuario().getNombre(),
                turno.getNotasTratamiento(),
                turno.getComentarios()
        )).toList();
    }

    public List<EntradaHistoriaClinicaDto> getHistoriaClinicaByAuth0Id(String auth0Id){
        Paciente paciente = pacienteRepository.findByUsuario_Auth0Id(auth0Id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));

        List<Turno> turnos = turnoRepository.findByPacienteIdOrderByFechaHoraDesc(paciente.getId());

        if(turnos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El paciente no tiene turnos");
        }

        return turnos.stream().map(turno -> new EntradaHistoriaClinicaDto(
                turno.getFechaHora(),
                turno.getDentista().getUsuario().getNombre(),
                turno.getNotasTratamiento(),
                turno.getComentarios()
        )).toList();
    }
}
