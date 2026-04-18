package com.ferreteriapsa.logistica.trabajador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ferreteriapsa.logistica.trabajador.dto.request.TrabajadorRequest;
import com.ferreteriapsa.logistica.trabajador.dto.response.TrabajadorDTO;
import com.ferreteriapsa.logistica.trabajador.service.TrabajadorService;

@RestController
@RequestMapping("/logistica/trabajadores")
public class TrabajadorController {
    private final TrabajadorService trabajadorService;

    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<TrabajadorDTO> registrarTrabajador(@RequestBody TrabajadorRequest request) {
        TrabajadorDTO nuevoTrabajador = trabajadorService.registrarTrabajadorCompleto(request);
        return ResponseEntity.ok(nuevoTrabajador);
    }
}
