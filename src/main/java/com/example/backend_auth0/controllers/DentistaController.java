package com.example.backend_auth0.controllers;

import com.example.backend_auth0.dto.DentistaDto;
import com.example.backend_auth0.entities.Dentista;
import com.example.backend_auth0.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(path = "/api/dentistas", produces = MediaType.APPLICATION_JSON_VALUE)
public class DentistaController {

    private final DentistaService dentistaService;

    @Autowired
    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @GetMapping
    public List<DentistaDto> findAll() {
        return dentistaService.getAllDentistas();
    }

}
