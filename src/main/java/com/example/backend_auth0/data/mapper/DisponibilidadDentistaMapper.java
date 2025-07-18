package com.example.backend_auth0.data.mapper;

import com.example.backend_auth0.data.entities.DisponibilidadDentista;
import com.example.backend_auth0.domain.dto.DisponibilidadDentistaDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = { UsuarioMapper.class, EspecialidadMapper.class },  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DisponibilidadDentistaMapper extends BaseMapper<DisponibilidadDentista, DisponibilidadDentistaDto> {
}
