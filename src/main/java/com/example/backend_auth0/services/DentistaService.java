package com.example.backend_auth0.services;

import com.example.backend_auth0.dto.DentistaDto;
import com.example.backend_auth0.dto.EspecialidadDto;
import com.example.backend_auth0.dto.UsuarioDto;
import com.example.backend_auth0.entities.Dentista;
import com.example.backend_auth0.repository.BaseRepository;
import com.example.backend_auth0.repository.DentistaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DentistaService extends BaseService<Dentista, Integer>  {

    protected final DentistaRepository dentistaRepository;

    public DentistaService(BaseRepository<Dentista, Integer> baseRepository, DentistaRepository dentistaRepository) {
        super(baseRepository);
        this.dentistaRepository = dentistaRepository;
    };

    public List<DentistaDto> getAllDentistas() {
        return dentistaRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /** Mapeo manual de entidad → DTO */
    private DentistaDto toDto(Dentista d) {
        DentistaDto dto = new DentistaDto();

        // Ponemos el ID de la entidad en el campo dentistaId del DTO
        dto.setDentistaId(d.getId());

        // Mapeamos los datos del usuario asociado
        UsuarioDto u = new UsuarioDto();
        u.setNombre(d.getUsuario().getNombre());
        u.setApellido(d.getUsuario().getApellido());
        u.setEmail(d.getUsuario().getEmail());
        // ... más campos de UsuarioDto si los tuvieras
        dto.setUsuario(u);

        // Mapeamos la especialidad
        EspecialidadDto e = new EspecialidadDto();
        e.setId(d.getEspecialidad().getId());
        e.setNombre(d.getEspecialidad().getNombre());
        dto.setEspecialidad(e);

        // Y la matrícula
        dto.setMatricula(d.getMatricula());

        return dto;
    }

}
