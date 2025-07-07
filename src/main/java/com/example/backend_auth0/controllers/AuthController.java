package com.example.backend_auth0.controllers;

import com.example.backend_auth0.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@AuthenticationPrincipal Jwt jwt) {
        boolean isLogged = authService.login(jwt);

        if(isLogged){
            return ResponseEntity.ok("Login exitoso");
        } else{
            return ResponseEntity.badRequest().build();
        }


    }

}
