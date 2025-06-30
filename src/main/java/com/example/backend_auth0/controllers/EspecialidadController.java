package com.example.backend_auth0.controllers;

import com.example.backend_auth0.repository.EspecialidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "test")
public class EspecialidadController {
    @Autowired
    EspecialidadesRepository especialidadesRepo;

    @GetMapping("")
    public ResponseEntity<?> getAll() {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(especialidadesRepo.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

}
