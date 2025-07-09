package com.example.backend_auth0.presentation.controllers.base;

import com.example.backend_auth0.data.entities.BaseEntity;
import com.example.backend_auth0.domain.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class ReadOnlyBaseController<E extends BaseEntity, ID extends Serializable, D> {

    @Autowired
    protected BaseService<E, ID> service;

    // @GetMapping
    // public ResponseEntity<List<D>> getAll() throws Exception {
    // List<E> entities = service.findAll();
    // List<D> dtos = entities.stream().map(this::toDto).toList();
    // return ResponseEntity.ok(dtos);
    // }

    protected abstract D toDto(E entity);

    // @GetMapping("/{id}")
    // public ResponseEntity<D> getById(@PathVariable ID id) throws Exception {
    // E entity = service.findById(id);
    // D dto = toDto(entity);
    // return ResponseEntity.ok(dto);
    // }

}