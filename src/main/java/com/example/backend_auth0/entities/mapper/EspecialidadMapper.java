package com.example.backend_auth0.entities.mapper;

import com.example.backend_auth0.dto.EspecialidadDto;
import com.example.backend_auth0.entities.Especialidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EspecialidadMapper {

    EspecialidadMapper Instancia = Mappers.getMapper(EspecialidadMapper.class);

    @Mapping(source = "nombre", target = "nombre")
    EspecialidadDto especialidadToEspecialidadDto(Especialidad especialidad);

    @Mapping(source = "nombre", target = "nombre")
    Especialidad especialidadDtoToEspecialidad(EspecialidadDto especialidadDto);
}
