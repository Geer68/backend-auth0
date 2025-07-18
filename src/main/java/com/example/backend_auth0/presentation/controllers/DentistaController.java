package com.example.backend_auth0.presentation.controllers;

import com.example.backend_auth0.data.entities.Dentista;
import com.example.backend_auth0.data.mapper.DentistaMapperImpl;
import com.example.backend_auth0.domain.dto.DentistaDto;
import com.example.backend_auth0.domain.services.DentistaService;
import java.util.List;

import com.example.backend_auth0.presentation.dto.request.ActualizarDentistaRequest;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dentista")
public class DentistaController {
    private final DentistaService dentistaService;
    private final DentistaMapperImpl dentistaMapperImpl;

    @Autowired
    public DentistaController(DentistaService dentistaService, DentistaMapperImpl dentistaMapperImpl) {
        this.dentistaService = dentistaService;
        this.dentistaMapperImpl = dentistaMapperImpl;
    }

    @GetMapping
    public ResponseEntity<List<DentistaDto>> getAll() {
        List<DentistaDto> dentistas = dentistaService.findAllDtos();
        return ResponseEntity.ok(dentistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaDto> getById(@PathVariable Long id) {
        DentistaDto dentista = dentistaService.findDtoById(id);
        return ResponseEntity.ok(dentista);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/dar-de-baja/{id}")
    public ResponseEntity<DentistaDto> darDeBaja(@PathVariable Long id) {
        DentistaDto dentista = dentistaService.delete(id);
        return ResponseEntity.ok(dentista);
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'dentista')")
    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<DentistaDto> update(@PathVariable Long id, @Valid @RequestBody ActualizarDentistaRequest dentistaReq) {
        DentistaDto dentista = dentistaService.update(id, dentistaReq);
        return ResponseEntity.ok(dentista);
    }

    @PreAuthorize("hasAuthority('dentista')")
    @GetMapping("/test")
    public ResponseEntity<?> test(Authentication authentication) {
        return ResponseEntity.ok(authentication.getPrincipal());
    }
}