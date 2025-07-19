package com.example.backend_auth0.presentation.controllers;

import com.example.backend_auth0.data.repository.DisponibilidadDentistaRepository;
import com.example.backend_auth0.domain.dto.DisponibilidadDentistaDto;
import com.example.backend_auth0.domain.services.DisponibilidadDentistaService;
import com.example.backend_auth0.presentation.dto.request.ActualizarDisponibilidadDentistaRequest;
import com.example.backend_auth0.presentation.dto.request.CrearDisponibilidadDentistaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/disponibilidad-dentista")
public class DisponibilidadDentistaController {

    DisponibilidadDentistaService disponibilidadDentistaService;

    @Autowired
    public DisponibilidadDentistaController(DisponibilidadDentistaService disponibilidadDentistaService, DisponibilidadDentistaRepository disponibilidadDentistaRepository) {
        this.disponibilidadDentistaService = disponibilidadDentistaService;
        this.disponibilidadDentistaRepository = disponibilidadDentistaRepository;
    }

    @PreAuthorize("hasAnyAuthority('administrativo', 'dentista', 'paciente')")
    @GetMapping("/todos")
    public ResponseEntity<List<DisponibilidadDentistaDto>> getAll(){
        List<DisponibilidadDentistaDto> disponibilidadDentistaDtos = disponibilidadDentistaService.findAll();
        return ResponseEntity.ok(disponibilidadDentistaDtos);
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'dentista', 'paciente')")
    @GetMapping("/{dentistaId}")
    public ResponseEntity<List<DisponibilidadDentistaDto>> getByDentistaId(@PathVariable Long dentistaId) {
        List<DisponibilidadDentistaDto> disponibilidadDentista = disponibilidadDentistaService.getByDentistaId(dentistaId);
        return ResponseEntity.ok(disponibilidadDentista);
    }

    @PreAuthorize("hasAuthority('dentista')")
    @PostMapping("/crear")
    public ResponseEntity<DisponibilidadDentistaDto> create(@RequestBody CrearDisponibilidadDentistaRequest req) {
        DisponibilidadDentistaDto disponibilidadDentistaDto = disponibilidadDentistaService.create(req);

        return ResponseEntity.ok(disponibilidadDentistaDto);
    }

    @PreAuthorize("hasAuthority('dentista')")
    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<DisponibilidadDentistaDto> update(@PathVariable Long id, @RequestBody ActualizarDisponibilidadDentistaRequest update) {
        DisponibilidadDentistaDto disponibilidadDentistaDto = disponibilidadDentistaService.updateById(id, update);

        return ResponseEntity.ok(disponibilidadDentistaDto);
    }

    @PreAuthorize("hasAuthority('dentista')")
    @DeleteMapping("/eliminar/{disponibilidadId}")
    public ResponseEntity<DisponibilidadDentistaDto> deleteById(@PathVariable Long disponibilidadId){
        DisponibilidadDentistaDto disponibilidadDentistaDto = disponibilidadDentistaService.deleteById(disponibilidadId);

        return ResponseEntity.ok(disponibilidadDentistaDto);
    }
}
