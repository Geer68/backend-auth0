package com.example.backend_auth0.domain.services;

import com.example.backend_auth0.data.entities.Dentista;
import com.example.backend_auth0.data.mapper.DentistaMapper;
import com.example.backend_auth0.data.repository.DentistaRepository;
import com.example.backend_auth0.domain.dto.DentistaDto;
import com.example.backend_auth0.domain.services.base.BaseService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class DentistaService extends BaseService<Dentista, DentistaDto> {

    private final DentistaRepository dentistaRepository;
    private final DentistaMapper dentistaMapper;

    public DentistaService(DentistaRepository dentistaRepository, DentistaMapper dentistaMapper) {
        this.dentistaRepository = dentistaRepository;
        this.dentistaMapper = dentistaMapper;
        this.repository = dentistaRepository;
        this.mapper = dentistaMapper;
    }

    public List<DentistaDto> findAllDtos() {
        return dentistaRepository.findAll()
                .stream()
                .map(dentistaMapper::toDto)
                .collect(Collectors.toList());
    }

    public DentistaDto findDtoById(Integer id) {
        Dentista dentista = dentistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dentista no encontrado"));
        return dentistaMapper.toDto(dentista);
    }
}
