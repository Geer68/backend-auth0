package com.example.backend_auth0.presentation.controllers;

import com.example.backend_auth0.data.entities.Especialidad;
import com.example.backend_auth0.domain.dto.EspecialidadDto;
import com.example.backend_auth0.domain.services.EspecialidadService;
import com.example.backend_auth0.presentation.controllers.base.ReadOnlyBaseController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/especialidades")
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
    public ResponseEntity<EspecialidadDto> getById(@PathVariable Integer id) {
        EspecialidadDto especialidad = especialidadService.findDtoById(id);
        return ResponseEntity.ok(especialidad);
    }

}
