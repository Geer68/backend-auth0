package com.example.backend_auth0.data.mapper;

import com.example.backend_auth0.data.entities.Usuario;
import com.example.backend_auth0.domain.dto.UsuarioDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDto> {
    @Override
    UsuarioDto toDto(Usuario entity);

    @Override
    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    @Mapping(target = "actualizadoEn", ignore = true)
    Usuario toEntity(UsuarioDto dto);

}
