package com.example.backend_auth0.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class TestController {
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("{\"message\":\"Success. Just testing.\"}");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
