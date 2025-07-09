package com.example.backend_auth0.data.mapper;

import com.example.backend_auth0.data.entities.Administrador;
import com.example.backend_auth0.domain.dto.AdministradorDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class,  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdministradorMapper extends BaseMapper<Administrador, AdministradorDto> {

    @Override
    @Mapping(source = "usuario",  target = "usuario")
    AdministradorDto toDto(Administrador entity);

    @Override
    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Administrador toEntity(AdministradorDto dto);
}