package com.example.backend_auth0.data.mapper;

import com.example.backend_auth0.data.entities.BaseEntity;

public interface BaseMapper<E extends BaseEntity, D> {
    D toDto(E entity);

    E toEntity(D dto);
}