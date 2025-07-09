package com.example.backend_auth0.domain.services.base;

import com.example.backend_auth0.data.entities.BaseEntity;
import com.example.backend_auth0.data.mapper.BaseMapper;
import com.example.backend_auth0.data.repository.base.BaseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;

public class BaseService<E extends BaseEntity, D> {

    @Autowired
    protected BaseRepository<E, Integer> repository;

    @Autowired
    protected BaseMapper<E, D> mapper;

    public List<D> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public D findById(Integer id) {
        E entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        return mapper.toDto(entity);
    }
}