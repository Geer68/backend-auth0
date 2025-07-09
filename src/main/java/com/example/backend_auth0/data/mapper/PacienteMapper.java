package com.example.backend_auth0.data.mapper;

import com.example.backend_auth0.data.entities.Paciente;
import com.example.backend_auth0.domain.dto.PacienteDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class,  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PacienteMapper extends BaseMapper<Paciente, PacienteDto> {

    @Override
    @Mapping(source = "id",       target = "id")
    @Mapping(source = "usuario",  target = "usuario")
    PacienteDto toDto(Paciente entity);

    @Override
    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Paciente toEntity(PacienteDto dto);
}