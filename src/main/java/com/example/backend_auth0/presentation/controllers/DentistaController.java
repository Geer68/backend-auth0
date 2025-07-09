package com.example.backend_auth0.presentation.controllers;

import com.example.backend_auth0.domain.dto.DentistaDto;
import com.example.backend_auth0.domain.services.DentistaService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {
    private final DentistaService dentistaService;

    @Autowired
    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @GetMapping
    public ResponseEntity<List<DentistaDto>> getAll() {
        List<DentistaDto> dentistas = dentistaService.findAllDtos();
        return ResponseEntity.ok(dentistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaDto> getById(@PathVariable Integer id) {
        DentistaDto dentista = dentistaService.findDtoById(id);
        return ResponseEntity.ok(dentista);
    }

}