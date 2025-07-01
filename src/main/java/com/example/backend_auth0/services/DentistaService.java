package com.example.backend_auth0.services;

import com.example.backend_auth0.dto.DentistaDto;
import com.example.backend_auth0.repository.DentistaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistaService {
    @Autowired
    protected DentistaRepository dentistaRepository;

    @Transactional
    public List<DentistaDto> getAllDentistas() throws Exception {
        return dentistaRepository.findAll();
    }

}
