package com.example.backend_auth0.data.mapper;

import com.example.backend_auth0.data.entities.Usuario;
import com.example.backend_auth0.domain.dto.UsuarioDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.OffsetDateTime;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDto>{
    @Override
    @Mapping(source = "creadoEn",      target = "creadoEn",      qualifiedByName = "offsetToInstant")
    @Mapping(source = "actualizadoEn", target = "actualizadoEn", qualifiedByName = "offsetToInstant")
    UsuarioDto toDto(Usuario entity);

    @Named("offsetToInstant")
    default Instant offsetToInstant(OffsetDateTime odt) {
        return odt == null
                ? null
                : odt.toInstant();
    }

    @Override
    @InheritInverseConfiguration
    @Mapping(target = "id",            ignore = true)
    @Mapping(target = "creadoEn",      ignore = true)
    @Mapping(target = "actualizadoEn", ignore = true)
    Usuario toEntity(UsuarioDto dto);

}
