package com.example.backend_auth0.presentation.dto.response;

import com.example.backend_auth0.domain.dto.AdministradorDto;
import com.example.backend_auth0.domain.dto.DentistaDto;
import com.example.backend_auth0.domain.dto.PacienteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String role;
    private PacienteDto patient;
    private DentistaDto dentist;
    private AdministradorDto administrator;
}