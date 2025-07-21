package com.example.backend_auth0.presentation.controllers;

import com.example.backend_auth0.domain.dto.EspecialidadDto;
import com.example.backend_auth0.domain.services.EspecialidadService;

import java.util.List;

import com.example.backend_auth0.presentation.dto.request.EspecialidadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {
    private final EspecialidadService especialidadService;

    @Autowired
    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadDto>> getAll() {
        List<EspecialidadDto> especialidades = especialidadService.findAllDtos();
        return ResponseEntity.ok(especialidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadDto> getById(@PathVariable Long id) {
        EspecialidadDto especialidad = especialidadService.findDtoById(id);
        return ResponseEntity.ok(especialidad);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/crear")
    public ResponseEntity<EspecialidadDto> add(@RequestBody EspecialidadRequest especialidadReq) {
        EspecialidadDto especialidad = especialidadService.add(especialidadReq.getNombre());
        return ResponseEntity.ok(especialidad);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @PatchMapping("/{id}")
    public ResponseEntity<EspecialidadDto> update(@RequestBody EspecialidadRequest especialidadReq, @PathVariable Long id) {
        EspecialidadDto especialidad = especialidadService.update(id, especialidadReq);
        return ResponseEntity.ok(especialidad);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @DeleteMapping("/{id}")
    public ResponseEntity<EspecialidadDto> delete(@PathVariable Long id) {
        EspecialidadDto especialidad = especialidadService.delete(id);
        return ResponseEntity.ok(especialidad);
    }

}
