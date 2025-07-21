package com.example.backend_auth0.presentation.controllers;

import com.example.backend_auth0.domain.dto.AdministradorDto;
import com.example.backend_auth0.domain.services.AdministradorService;
import com.example.backend_auth0.presentation.dto.request.CambiarRolRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdministradorController {
    private AdministradorService administradorService;

    @Autowired
    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }


    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping
    public ResponseEntity<List<AdministradorDto>> getAll() {
        List<AdministradorDto> administradores = administradorService.getAll();
        return ResponseEntity.ok(administradores);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDto> getById(@PathVariable Long id) {
        AdministradorDto administrador = administradorService.getById(id);
        return ResponseEntity.ok(administrador);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @PatchMapping("/api/usuarios/{id}/rol")
    public ResponseEntity<Void> cambiarRol(
            @PathVariable Long id,
            @RequestBody @Valid CambiarRolRequest request
    ) {
        administradorService.cambiarRol(id, request);
        return ResponseEntity.ok().build();
    }


}
