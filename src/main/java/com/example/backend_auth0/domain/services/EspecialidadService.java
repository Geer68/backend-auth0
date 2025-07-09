package com.example.backend_auth0.domain.services;

import com.example.backend_auth0.data.entities.Especialidad;
import com.example.backend_auth0.data.mapper.EspecialidadMapper;
import com.example.backend_auth0.data.repository.EspecialidadRepository;
import com.example.backend_auth0.domain.dto.EspecialidadDto;
import com.example.backend_auth0.domain.services.base.BaseService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class EspecialidadService extends BaseService<Especialidad, EspecialidadDto> {

    private final EspecialidadRepository especialidadRepository;
    private final EspecialidadMapper especialidadMapper;

    public EspecialidadService(EspecialidadRepository especialidadRepository, EspecialidadMapper especialidadMapper) {
        this.especialidadRepository = especialidadRepository;
        this.especialidadMapper = especialidadMapper;
        this.repository = especialidadRepository;
        this.mapper = especialidadMapper;
    }

    public List<EspecialidadDto> findAllDtos() {
        return especialidadRepository.findAll()
                .stream()
                .map(especialidadMapper::toDto)
                .collect(Collectors.toList());
    }

    public EspecialidadDto findDtoById(Integer id) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        return especialidadMapper.toDto(especialidad);
    }
}
