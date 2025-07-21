package com.example.backend_auth0.presentation.controllers;

import com.example.backend_auth0.data.enums.EstadoTurno;
import com.example.backend_auth0.domain.dto.TurnoDto;
import com.example.backend_auth0.domain.services.TurnoService;
import com.example.backend_auth0.presentation.dto.request.ActualizarEstadoTurno;
import com.example.backend_auth0.presentation.dto.request.ActualizarTurnoRequest;
import com.example.backend_auth0.presentation.dto.request.CrearTurnoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/turno")
public class TurnoController {

    private TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping()
    public ResponseEntity<List<TurnoDto>> getAll(){
        List<TurnoDto> turnos = turnoService.getAll();

        return ResponseEntity.ok(turnos);
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'dentista', 'paciente')")
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> getById(@PathVariable Long id){
        TurnoDto turno = turnoService.getById(id);
        return ResponseEntity.ok(turno);
    }


    @PreAuthorize("hasAnyAuthority('administrador', 'dentista', 'paciente')")
    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<TurnoDto>> getByPacienteId(@PathVariable Long id){
        List<TurnoDto> turnos = turnoService.getTurnoByPacienteId(id);

        return ResponseEntity.ok(turnos);
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'dentista')")
    @GetMapping("/dentista/{id}")
    public ResponseEntity<List<TurnoDto>> getTurnoFromDentista(@PathVariable Long id){
        List<TurnoDto> turnos = turnoService.getTurnoByDentistaId(id);

        return ResponseEntity.ok(turnos);
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'dentista')")
    @PostMapping("/crear")
    public ResponseEntity<TurnoDto> create(@RequestBody CrearTurnoRequest turnoRequest){
        TurnoDto turnoDto = turnoService.create(turnoRequest);

        return ResponseEntity.ok(turnoDto);
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'dentista')")
    @PatchMapping("/estado/{id}")
    public ResponseEntity<TurnoDto> cambiarEstado(@PathVariable Long id, @RequestBody ActualizarEstadoTurno estado) {
        System.out.println(estado);
        TurnoDto actualizado = turnoService.cambiarEstado(id, estado);
        return ResponseEntity.ok(actualizado);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @DeleteMapping("/{id}")
    public ResponseEntity<TurnoDto> delete(@PathVariable Long id) {
        TurnoDto turno = turnoService.delete(id);
        return ResponseEntity.ok(turno);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/{id}")
    public ResponseEntity<TurnoDto> cancel(@PathVariable Long id) {
        TurnoDto turno = turnoService.cancelar(id);
        return ResponseEntity.ok(turno);
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'dentista')")
    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<TurnoDto> update(@PathVariable Long id, @RequestBody ActualizarTurnoRequest req){
        System.out.println(req);
        TurnoDto turno = turnoService.update(id, req);
        return ResponseEntity.ok(turno);
    }
}
