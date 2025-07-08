package com.example.backend_auth0.services;

import com.example.backend_auth0.entities.Usuario;
import com.example.backend_auth0.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean createAccount(String auth0Id, String nombre, String email) {
        Optional<Usuario> user = usuarioRepository.findByAuth0Id(auth0Id);

        if(user.isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setNombre(nombre);
            usuario.setAuth0Id(auth0Id);
            usuario.setApellido(nombre);
            usuarioRepository.save(usuario);
        }

        return true;
    }


}
