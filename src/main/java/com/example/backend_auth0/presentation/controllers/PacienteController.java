package com.example.backend_auth0.presentation.controllers;

import com.example.backend_auth0.domain.dto.EntradaHistoriaClinicaDto;
import com.example.backend_auth0.domain.dto.PacienteDto;
import com.example.backend_auth0.domain.services.PacienteService;
import com.example.backend_auth0.presentation.dto.request.ActualizarPacienteRequest;
import com.example.backend_auth0.presentation.dto.request.CrearPacienteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping
    public ResponseEntity<List<PacienteDto>> getAll() {
        List<PacienteDto> pacientes = pacienteService.getAll();
        return ResponseEntity.ok(pacientes);
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'dentista')")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> getById(@PathVariable Long id) {
        PacienteDto paciente = pacienteService.getById(id);
        return ResponseEntity.ok(paciente);
    }

    @PreAuthorize("hasAuthority('paciente')")
    @GetMapping("/me")
    public ResponseEntity<PacienteDto> getMyData(@AuthenticationPrincipal Jwt principal) {
        String auth0Id = principal.getClaimAsString("sub");
        PacienteDto paciente = pacienteService.getByAuth0Id(auth0Id);
        return ResponseEntity.ok(paciente);
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'dentista')")
    @GetMapping("/buscar")
    public ResponseEntity<PacienteDto> buscarPaciente(@RequestParam(required = false) String email,
                                                      @RequestParam(required = false) String dni) {
        if ((email == null || email.isBlank()) && (dni == null || dni.isBlank())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debes proporcionar un email o DNI para la búsqueda.");
        }
        PacienteDto paciente = pacienteService.buscarPorEmailODni(email, dni);
        return ResponseEntity.ok(paciente);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping
    public ResponseEntity<PacienteDto> createPaciente(@RequestBody CrearPacienteRequest req) {
        PacienteDto nuevo = pacienteService.create(req);
        return ResponseEntity.ok(nuevo);
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'paciente')")
    @PatchMapping("/{id}")
    public ResponseEntity<PacienteDto> updatePaciente(@PathVariable Long id,
                                                      @RequestBody ActualizarPacienteRequest req) {
        PacienteDto actualizado = pacienteService.update(id, req);
        return ResponseEntity.ok(actualizado);
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'dentista')")
    @GetMapping("/{id}/historia-clinica")
    public ResponseEntity<Object> getHistoriaClinica(@PathVariable Long id) {
        List<EntradaHistoriaClinicaDto> historiaClinica = pacienteService.getHistoriaClinica(id); // puede devolver un DTO personalizado
        return ResponseEntity.ok(historiaClinica);
    }

    @PreAuthorize("hasAuthority('paciente')")
    @GetMapping("/me/historia-clinica")
    public ResponseEntity<Object> getMyHistoriaClinica(@AuthenticationPrincipal Jwt principal) {
        String auth0Id = principal.getClaimAsString("sub");
        List<EntradaHistoriaClinicaDto> historiaClinica = pacienteService.getHistoriaClinicaByAuth0Id(auth0Id);
        return ResponseEntity.ok(historiaClinica);
    }
}
