package com.example.backend_auth0.controllers;

import com.example.backend_auth0.dto.DatosCreacionCuenta;
import com.example.backend_auth0.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/account-create")
    public ResponseEntity<?> createAccount(@RequestBody DatosCreacionCuenta req) {
        String auth0Id = req.getAuth0Id();
        String nombre = req.getNombre();
        String email = req.getEmail();

        log.info(auth0Id);
        log.info(nombre);
        log.info(email);

        if (!auth0Id.matches("^[^|]+\\|[^|]+$")) {
            System.out.println(auth0Id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        boolean isCreated = authService.createAccount(auth0Id, nombre, email);

        if(isCreated){
            return ResponseEntity.ok("Login exitoso");
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }

}
