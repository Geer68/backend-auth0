package com.example.backend_auth0.repository;

import com.example.backend_auth0.entities.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistaRepository extends JpaRepository<Dentista, Integer> {
}
