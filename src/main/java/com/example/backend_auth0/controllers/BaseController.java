package com.example.backend_auth0.controllers;


import com.example.backend_auth0.entities.BaseEntity;
import com.example.backend_auth0.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseController<E extends BaseEntity, S extends BaseService<E,Integer>> {
    @Autowired
    protected S servicio;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
        }
    }

//    @PostMapping("")
//    public ResponseEntity<?> save(@RequestBody E entity){
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(entity));
//        }catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody E entity){
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id,entity));
//        }catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Integer id) {
//        try {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.delete(id));
//        }catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
//        }
//    }
}