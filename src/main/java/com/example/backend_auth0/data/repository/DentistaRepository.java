package com.example.backend_auth0.data.repository;


import com.example.backend_auth0.data.entities.Dentista;
import com.example.backend_auth0.data.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends BaseRepository<Dentista, Integer> {
}
