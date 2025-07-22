package com.example.backend_auth0.domain.services;

import com.example.backend_auth0.data.entities.Dentista;
import com.example.backend_auth0.data.entities.DisponibilidadDentista;
import com.example.backend_auth0.data.mapper.DisponibilidadDentistaMapper;
import com.example.backend_auth0.data.repository.DentistaRepository;
import com.example.backend_auth0.data.repository.DisponibilidadDentistaRepository;
import com.example.backend_auth0.domain.dto.DisponibilidadDentistaDto;
import com.example.backend_auth0.domain.services.base.BaseService;
import com.example.backend_auth0.presentation.dto.request.ActualizarDisponibilidadDentistaRequest;
import com.example.backend_auth0.presentation.dto.request.CrearDisponibilidadDentistaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class DisponibilidadDentistaService extends BaseService<DisponibilidadDentista, DisponibilidadDentistaDto> {

    DisponibilidadDentistaRepository disponibilidadDentistaRepository;
    DisponibilidadDentistaMapper disponibilidadDentistaMapper;
    DentistaRepository dentistaRepository;

    @Autowired
    public DisponibilidadDentistaService(DisponibilidadDentistaRepository disponibilidadDentistaRepository, DisponibilidadDentistaMapper disponibilidadDentistaMapper, DentistaRepository dentistaRepository) {
        this.disponibilidadDentistaRepository = disponibilidadDentistaRepository;
        this.disponibilidadDentistaMapper = disponibilidadDentistaMapper;
        this.dentistaRepository = dentistaRepository;
    }

    public List<DisponibilidadDentistaDto> findAll() {
        List<DisponibilidadDentista> disponibilidadDentistas = disponibilidadDentistaRepository.findAll();
        if(disponibilidadDentistas.isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "No Disponibilidad Dentista");
        }

        return disponibilidadDentistas.stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<DisponibilidadDentistaDto> getByDentistaId(Long dentistaId){
        List<DisponibilidadDentista> disponibilidadDentista = disponibilidadDentistaRepository.findByDentistaIdAndDentistaUsuarioDeletedAtIsNull(dentistaId);

        if(disponibilidadDentista.isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Disponibilidades no encontradas");
        }

        return disponibilidadDentista.stream()
                .map(mapper::toDto)
                .toList();
    }

    public DisponibilidadDentistaDto create(CrearDisponibilidadDentistaRequest req) {
        Dentista dentista = dentistaRepository.findById(req.getDentistaId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Dentista no encontrado"));

        DisponibilidadDentista disponibilidadDentistaNueva = new DisponibilidadDentista();
        disponibilidadDentistaNueva.setDentista(dentista);

        disponibilidadDentistaNueva.setDiaSemana(req.getDiaSemana());
        disponibilidadDentistaNueva.setHoraInicio(req.getHoraInicio());
        disponibilidadDentistaNueva.setHoraFin(req.getHoraFin());

        disponibilidadDentistaRepository.save(disponibilidadDentistaNueva);

        return mapper.toDto(disponibilidadDentistaNueva);
    }

    public DisponibilidadDentistaDto updateById(Long id, ActualizarDisponibilidadDentistaRequest req){
        DisponibilidadDentista disponibilidadDentista = disponibilidadDentistaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Disponibilidad no encontrada"));

        if (req.getDiaSemana() != null) {
            disponibilidadDentista.setDiaSemana(req.getDiaSemana());
        }
        if (req.getHoraInicio() != null) {
            disponibilidadDentista.setHoraInicio(req.getHoraInicio());
        }
        if (req.getHoraFin() != null) {
            disponibilidadDentista.setHoraFin(req.getHoraFin());
        }

        disponibilidadDentistaRepository.save(disponibilidadDentista);
        return mapper.toDto(disponibilidadDentista);

    }

    public DisponibilidadDentistaDto deleteById(Long disponibilidadId) {
        DisponibilidadDentista disponibilidadDentista = disponibilidadDentistaRepository.findById(disponibilidadId)
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Disponibilidad no encontrada"));

        disponibilidadDentista.setDeletedAt(OffsetDateTime.now());

        disponibilidadDentistaRepository.save(disponibilidadDentista);

        return disponibilidadDentistaMapper.toDto(disponibilidadDentista);
    }
}
