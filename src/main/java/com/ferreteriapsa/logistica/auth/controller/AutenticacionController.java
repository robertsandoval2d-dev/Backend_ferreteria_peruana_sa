package com.ferreteriapsa.logistica.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ferreteriapsa.logistica.auth.dto.request.UsuarioRequest;
import com.ferreteriapsa.logistica.auth.dto.response.AuthResponse;
import com.ferreteriapsa.logistica.auth.service.AutenticacionService;

@CrossOrigin(origins = "http://localhost:4200") // ADDED
@RestController
@RequestMapping("/logistica/auth")
public class AutenticacionController {
    private final AutenticacionService autenticacionService;

    public AutenticacionController(AutenticacionService autenticacionService) {
        this.autenticacionService = autenticacionService;
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UsuarioRequest request) {
        AuthResponse usuario = autenticacionService.login(request);
        return ResponseEntity.ok(usuario);
    }

}
