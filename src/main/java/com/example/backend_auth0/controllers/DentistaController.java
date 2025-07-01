package com.example.backend_auth0.controllers;

import com.example.backend_auth0.entities.Dentista;
import com.example.backend_auth0.services.DentistaService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(path = "/api/dentistas", produces = MediaType.APPLICATION_JSON_VALUE)
public class DentistaController extends BaseController<Dentista, DentistaService> {


}
