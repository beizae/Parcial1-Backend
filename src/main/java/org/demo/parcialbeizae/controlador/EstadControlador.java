package org.demo.parcialbeizae.controlador;

import org.demo.parcialbeizae.dto.EstadResponse;
import org.demo.parcialbeizae.servicios.EstadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Estado")
public class EstadControlador {

    private final EstadService estadService;

    public EstadControlador(EstadService estadService) {
        this.estadService = estadService;
    }

    @GetMapping
    public EstadResponse getStats() {
        return estadService.getStats();
    }
}