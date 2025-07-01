package com.example.backend_auth0.controllers;

import com.example.backend_auth0.dto.EspecialidadDto;
import com.example.backend_auth0.entities.Especialidad;
import com.example.backend_auth0.entities.mapper.EspecialidadMapper;
import com.example.backend_auth0.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping(path = "test")
public class EspecialidadController {
    @Autowired
    EspecialidadRepository especialidadRepository;

    @GetMapping("")
    public ResponseEntity<?> getAll() {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(especialidadRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getById(@PathVariable Integer id) {
//        Optional<Especialidad> isEspecialidad = especialidadRepository.findById(id);
//        if (isEspecialidad.isPresent()) {
//            Especialidad especialidad = isEspecialidad.get();
//            EspecialidadDto returnValor = EspecialidadMapper.Instancia.especialidadToEspecialidadDto(especialidad);
//            return ResponseEntity.status(HttpStatus.OK).body(returnValor);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
//        }
//
//    }

}
