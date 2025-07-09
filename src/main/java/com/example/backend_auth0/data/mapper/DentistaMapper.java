package com.example.backend_auth0.data.mapper;

import com.example.backend_auth0.data.entities.Dentista;
import com.example.backend_auth0.domain.dto.DentistaDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { UsuarioMapper.class, EspecialidadMapper.class })
public interface DentistaMapper extends BaseMapper<Dentista, DentistaDto> {

    @Override
    @Mapping(source = "id", target = "dentistaId")
    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "especialidad", target = "especialidad")
    DentistaDto toDto(Dentista entity);

    @Override
    @InheritInverseConfiguration
    Dentista toEntity(DentistaDto dto);
}
