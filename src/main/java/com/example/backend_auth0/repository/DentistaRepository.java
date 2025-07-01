package com.example.backend_auth0.repository;

import com.example.backend_auth0.entities.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends BaseRepository<Dentista, Integer> {
}
