package com.ferreteriapsa.logistica.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ferreteriapsa.logistica.auth.dto.request.UsuarioRequest;
import com.ferreteriapsa.logistica.auth.dto.response.UsuarioDTO;
import com.ferreteriapsa.logistica.auth.service.AutenticacionService;

@RestController
@RequestMapping("/logistica/auth")
public class AutenticacionController {
    private final AutenticacionService autenticacionService;

    public AutenticacionController(AutenticacionService autenticacionService) {
        this.autenticacionService = autenticacionService;
    }

    //regitrar usuario
    // @PostMapping("/register")
    // public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioRequest request) {
    //     UsuarioDTO nuevoUsuario = autenticacionService.registrarUsuario(request);
    //     return ResponseEntity.ok(nuevoUsuario);
    // }

    //login
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioRequest request) {
        UsuarioDTO usuario = autenticacionService.login(request);
        return ResponseEntity.ok(usuario);
    }

}
