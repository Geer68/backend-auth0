package com.example.backend_auth0.data.mapper;

import com.example.backend_auth0.data.entities.Especialidad;
import com.example.backend_auth0.domain.dto.EspecialidadDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EspecialidadMapper extends BaseMapper<Especialidad, EspecialidadDto> {
}
