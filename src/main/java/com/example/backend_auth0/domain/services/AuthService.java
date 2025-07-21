package com.example.backend_auth0.domain.services;

import com.example.backend_auth0.data.entities.Paciente;
import com.example.backend_auth0.data.entities.Usuario;
import com.example.backend_auth0.data.mapper.AdministradorMapper;
import com.example.backend_auth0.data.mapper.DentistaMapper;
import com.example.backend_auth0.data.mapper.PacienteMapper;
import com.example.backend_auth0.data.repository.AdministradorRepository;
import com.example.backend_auth0.data.repository.DentistaRepository;
import com.example.backend_auth0.data.repository.PacienteRepository;
import com.example.backend_auth0.data.repository.UsuarioRepository;
import com.example.backend_auth0.domain.dto.AdministradorDto;
import com.example.backend_auth0.domain.dto.DentistaDto;
import com.example.backend_auth0.domain.dto.PacienteDto;
import com.example.backend_auth0.presentation.dto.request.CrearUsuarioRequest;
import com.example.backend_auth0.presentation.dto.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final DentistaRepository dentistaRepository;
    private final AdministradorRepository administradorRepository;
    private final Auth0ManagementService auth0MgmtService;

    private final PacienteMapper pacienteMapper;
    private final DentistaMapper dentistaMapper;
    private final AdministradorMapper administradorMapper;

    @Transactional
    public AuthResponse loginAndGetRole(CrearUsuarioRequest req, Collection<? extends GrantedAuthority> authorities){
        boolean isNew = usuarioRepository.findByAuth0Id(req.getAuth0Id()).isEmpty();

        Usuario usuario = usuarioRepository.findByAuth0Id(req.getAuth0Id()).orElseGet(() -> {
            Usuario u = new Usuario();
            u.setAuth0Id(req.getAuth0Id());
            u.setNombre(req.getNombre());
            u.setApellido("Default");
            u.setEmail(req.getEmail());
            usuarioRepository.save(u);

            Paciente p = new Paciente();
            p.setUsuario(u);
            p.setObraSocial("Sin obra social");
            p.setTelefonoEmergencia("Sin teléfono de emergencia");
            pacienteRepository.save(p);
            auth0MgmtService.assignRolesToUser(req.getAuth0Id(),
                    List.of("rol_Xs6RxbMgq4JeGEGN"));
            return u;
        });

        if (isNew) {
            var dto = pacienteMapper.toDto(
                    pacienteRepository.findByUsuario(usuario).get()
            );
            return new AuthResponse("paciente", dto, null, null);
        }

        String jwtRole = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new AccessDeniedException("No se encontró rol en el token"));

        PacienteDto patientDto = null;
        DentistaDto dentistDto = null;
        AdministradorDto administradorDto = null;


        switch (jwtRole) {
            case "paciente" -> {
                var p = pacienteRepository.findByUsuario(usuario)
                        .orElseThrow(() -> new AccessDeniedException("No paciente"));
                patientDto = pacienteMapper.toDto(p);
            }
            case "dentista" -> {
                var d = dentistaRepository.findByUsuario(usuario)
                        .orElseThrow(() -> new AccessDeniedException("No dentista"));
                dentistDto = dentistaMapper.toDto(d);
            }
            case "administrador" -> {
                var a = administradorRepository.findByUsuario(usuario)
                        .orElseThrow(() -> new AccessDeniedException("No admin"));
                administradorDto = administradorMapper.toDto(a);
            }
            default -> throw new AccessDeniedException("Acceso Denegado");
        }

        return new AuthResponse(jwtRole, patientDto, dentistDto, administradorDto);
    }
}
