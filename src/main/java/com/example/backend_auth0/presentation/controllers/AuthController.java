package com.example.backend_auth0.presentation.controllers;

import com.example.backend_auth0.presentation.dto.request.CrearUsuarioRequest;
import com.example.backend_auth0.domain.services.AuthService;
import com.example.backend_auth0.presentation.dto.response.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody CrearUsuarioRequest req,
            Authentication authentication
    ) {
        return authService.loginAndGetRole(
                req,
                authentication.getAuthorities()
        );
    }
}
