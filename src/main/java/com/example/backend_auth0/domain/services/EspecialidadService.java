package com.example.backend_auth0.domain.services;

import com.example.backend_auth0.data.entities.Especialidad;
import com.example.backend_auth0.data.mapper.EspecialidadMapper;
import com.example.backend_auth0.data.repository.EspecialidadRepository;
import com.example.backend_auth0.domain.dto.EspecialidadDto;
import com.example.backend_auth0.domain.services.base.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.backend_auth0.presentation.dto.request.EspecialidadRequest;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadService extends BaseService<Especialidad, EspecialidadDto> {

    private final EspecialidadRepository especialidadRepository;
    private final EspecialidadMapper especialidadMapper;

    public EspecialidadService(EspecialidadRepository especialidadRepository, EspecialidadMapper especialidadMapper) {
        this.especialidadRepository = especialidadRepository;
        this.especialidadMapper = especialidadMapper;
        this.mapper = especialidadMapper;
    }

    public List<EspecialidadDto> findAllDtos() {
        return especialidadRepository.findAll()
                .stream()
                .map(especialidadMapper::toDto)
                .collect(Collectors.toList());
    }

    public EspecialidadDto findDtoById(Long id) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        return especialidadMapper.toDto(especialidad);
    }

    public EspecialidadDto add(String nombre) {
        Optional<Especialidad> entity = especialidadRepository.findByNombre(nombre);
        if(entity.isPresent()) {
            throw new RuntimeException("Especialidad con nombre " + nombre  + " existente");
        }
        Especialidad especialidad = new Especialidad(nombre);
        especialidadRepository.save(especialidad);
        return especialidadMapper.toDto(especialidad);
    }

    public EspecialidadDto update(Long id, EspecialidadRequest especialidadReq) {
        Especialidad entity = especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        entity.setNombre(especialidadReq.getNombre());
        especialidadRepository.save(entity);
        return especialidadMapper.toDto(entity);
    }

    public EspecialidadDto delete(Long id) {
        Especialidad entity = especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        especialidadRepository.delete(entity);
        return especialidadMapper.toDto(entity);
    }
}
