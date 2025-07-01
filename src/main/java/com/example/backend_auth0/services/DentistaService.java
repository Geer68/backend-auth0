package com.example.backend_auth0.services;

import com.example.backend_auth0.dto.DentistaDto;
import com.example.backend_auth0.entities.Dentista;
import com.example.backend_auth0.repository.BaseRepository;
import com.example.backend_auth0.repository.DentistaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistaService extends BaseService<Dentista, Integer>  {

    @Autowired
    protected final DentistaRepository dentistaRepository;

    public DentistaService(BaseRepository<Dentista, Integer> baseRepository, DentistaRepository dentistaRepository) {
        super(baseRepository);
        this.dentistaRepository = dentistaRepository;
    };

}
