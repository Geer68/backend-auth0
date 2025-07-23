package com.example.backend_auth0.domain.services;

import com.example.backend_auth0.data.entities.Dentista;
import com.example.backend_auth0.data.entities.Paciente;
import com.example.backend_auth0.data.entities.Turno;
import com.example.backend_auth0.data.enums.EstadoTurno;
import com.example.backend_auth0.data.repository.DentistaRepository;
import com.example.backend_auth0.data.repository.PacienteRepository;
import com.example.backend_auth0.data.repository.TurnoRepository;
import com.example.backend_auth0.domain.dto.TurnoDto;
import com.example.backend_auth0.domain.services.base.BaseService;
import com.example.backend_auth0.presentation.dto.request.ActualizarEstadoTurno;
import com.example.backend_auth0.presentation.dto.request.ActualizarTurnoRequest;
import com.example.backend_auth0.presentation.dto.request.CrearTurnoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService extends BaseService<Turno, TurnoDto> {

    private TurnoRepository turnoRepository;
    private PacienteRepository pacienteRepository;
    private DentistaRepository dentistaRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteRepository pacienteRepository,
            DentistaRepository dentistaRepository) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.dentistaRepository = dentistaRepository;
    }

    public List<TurnoDto> getAll() {
        List<Turno> turnos = turnoRepository.findAll();

        if (turnos.isEmpty()) {
            return new ArrayList<>();
        }

        return turnos.stream()
                .map(mapper::toDto)
                .toList();
    }

    public TurnoDto getById(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe ese turno"));

        return mapper.toDto(turno);
    }

    public List<TurnoDto> getTurnoByPacienteId(Long pacienteId) {
        List<Turno> turnos = turnoRepository.findByPacienteId(pacienteId);
        if (turnos.isEmpty()) {
            return new ArrayList<>();
        }

        return turnos.stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<TurnoDto> getTurnoByDentistaId(Long dentistaId) {
        List<Turno> turnos = turnoRepository.findByDentistaId(dentistaId);
        if (turnos.isEmpty()) {
            return new ArrayList<>();
        }

        return turnos.stream()
                .map(mapper::toDto)
                .toList();
    }

    public TurnoDto create(CrearTurnoRequest req) {
        Dentista dentista = dentistaRepository.findById(req.getDentistaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dentista no encontrado"));

        Paciente paciente = pacienteRepository.findById(req.getPacienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));

        boolean yaExiste = turnoRepository.existsByDentistaIdAndPacienteIdAndFechaHora(req.getDentistaId(),
                req.getPacienteId(), req.getFechaHora());

        if (yaExiste) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ese turno ya existe");
        }

        Turno turno = new Turno();

        turno.setDentista(dentista);
        turno.setPaciente(paciente);
        turno.setEstado(EstadoTurno.PROGRAMADO);
        turno.setFechaHora(req.getFechaHora());
        turno.setCreadoEn(OffsetDateTime.now());

        turnoRepository.save(turno);

        return mapper.toDto(turno);
    }

    public TurnoDto update(Long id, ActualizarTurnoRequest req) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turno no encontrado"));

        turno.setEstado(req.getEstado());
        turno.setComentarios(req.getComentarios());
        turno.setNotasTratamiento(req.getNotasTratamiento());

        turnoRepository.save(turno);
        return mapper.toDto(turno);
    }

    public TurnoDto delete(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turno no encontrado"));

        turnoRepository.delete(turno);

        return mapper.toDto(turno);
    }

    public TurnoDto cancelar(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turno no encontrado"));

        turno.setEstado(EstadoTurno.CANCELADO);

        turnoRepository.save(turno);

        return mapper.toDto(turno);
    }

    public TurnoDto cambiarEstado(Long id, ActualizarEstadoTurno nuevoEstado) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turno no encontrado"));

        turno.setEstado(nuevoEstado.getEstado());
        turnoRepository.save(turno);
        return mapper.toDto(turno);
    }

    public TurnoDto editarTurno(Long id, CrearTurnoRequest req) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turno no encontrado"));

        Paciente paciente = pacienteRepository.findById(req.getPacienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
        turno.setPaciente(paciente);

        Dentista dentista = dentistaRepository.findById(req.getDentistaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dentista no encontrado"));
        turno.setDentista(dentista);

        turno.setEstado(req.getEstado());

        turno.setFechaHora(req.getFechaHora());

        turnoRepository.save(turno);
        return mapper.toDto(turno);
    }

}
