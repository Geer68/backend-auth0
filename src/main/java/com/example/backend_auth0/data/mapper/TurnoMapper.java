package com.example.backend_auth0.data.mapper;

import com.example.backend_auth0.data.entities.Turno;
import com.example.backend_auth0.domain.dto.TurnoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = { UsuarioMapper.class, DentistaMapper.class },  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TurnoMapper extends BaseMapper<Turno, TurnoDto> {
}
