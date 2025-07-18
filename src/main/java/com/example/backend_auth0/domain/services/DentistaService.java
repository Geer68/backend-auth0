package com.example.backend_auth0.domain.services;

import com.example.backend_auth0.data.entities.Dentista;
import com.example.backend_auth0.data.entities.Especialidad;
import com.example.backend_auth0.data.entities.Usuario;
import com.example.backend_auth0.data.mapper.DentistaMapper;
import com.example.backend_auth0.data.mapper.EspecialidadMapperImpl;
import com.example.backend_auth0.data.repository.DentistaRepository;
import com.example.backend_auth0.domain.dto.DentistaDto;
import com.example.backend_auth0.domain.dto.EspecialidadDto;
import com.example.backend_auth0.domain.services.base.BaseService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.backend_auth0.presentation.dto.request.ActualizarDentistaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class DentistaService extends BaseService<Dentista, DentistaDto> {

    private final DentistaRepository dentistaRepository;
    private final DentistaMapper dentistaMapper;
    private final EspecialidadService especialidadService;
    private final EspecialidadMapperImpl especialidadMapperImpl;

    @Autowired
    public DentistaService(DentistaRepository dentistaRepository, DentistaMapper dentistaMapper, EspecialidadService especialidadService, EspecialidadMapperImpl especialidadMapperImpl) {
        this.dentistaRepository = dentistaRepository;
        this.dentistaMapper = dentistaMapper;
        this.mapper = dentistaMapper;
        this.especialidadService = especialidadService;
        this.especialidadMapperImpl = especialidadMapperImpl;
    }

    public List<DentistaDto> findAllDtos() {
        return dentistaRepository.findAllByDeletedAtIsNull()
                .stream()
                .map(dentistaMapper::toDto)
                .collect(Collectors.toList());
    }

    public DentistaDto findDtoById(Long id) {
        Dentista dentista = dentistaRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Dentista no encontrado"));
        return dentistaMapper.toDto(dentista);
    }

    public DentistaDto update(Long id, ActualizarDentistaRequest dentistaReq) {
        Dentista dentista = dentistaRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Dentista no encontrado"));

        Usuario user = dentista.getUsuario();
        if(dentistaReq.getNombre() != null) user.setNombre(dentistaReq.getNombre());
        if(dentistaReq.getApellido() != null) user.setApellido(dentistaReq.getApellido());
        if(dentistaReq.getDni() != null) user.setDni(dentistaReq.getDni());
        if(dentistaReq.getEmail() != null) user.setEmail(dentistaReq.getEmail());
        if(dentistaReq.getTelefono() != null) user.setTelefono(dentistaReq.getTelefono());
        if(dentistaReq.getFechaNacimiento() != null) user.setFechaNacimiento(dentistaReq.getFechaNacimiento());

        if(dentistaReq.getMatricula() != null) dentista.setMatricula(dentistaReq.getMatricula());

        Long especialidadId = dentistaReq.getEspecialidadId();
        if(especialidadId != null) {
            EspecialidadDto especialidadDto = especialidadService.findById(especialidadId);
            Especialidad especialidad = especialidadMapperImpl.toEntity(especialidadDto);
            dentista.setEspecialidad(especialidad);
        }

        dentistaRepository.save(dentista);

        return dentistaMapper.toDto(dentista);
    }

    public DentistaDto delete(Long id) {
        Dentista dentista = dentistaRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Dentista no encontrado"));
        OffsetDateTime deletedAt = OffsetDateTime.now();
        dentista.setDeletedAt(deletedAt);
        dentistaRepository.save(dentista);
        return dentistaMapper.toDto(dentista);
    }
}
