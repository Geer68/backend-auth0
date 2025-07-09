package com.example.backend_auth0.data.mapper;

import com.example.backend_auth0.data.entities.Dentista;
import com.example.backend_auth0.domain.dto.DentistaDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = { UsuarioMapper.class, EspecialidadMapper.class },  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DentistaMapper extends BaseMapper<Dentista, DentistaDto> {

    @Override
    @Mapping(source = "usuario",      target = "usuario")
    @Mapping(source = "especialidad", target = "especialidad")
    @Mapping(source = "matricula",    target = "matricula")
    DentistaDto toDto(Dentista entity);

    @Override
    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Dentista toEntity(DentistaDto dto);
}