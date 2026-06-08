package com.ferreteriapsa.logistica.mensajeria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ferreteriapsa.logistica.auth.config.CustomUserPrincipal;
import com.ferreteriapsa.logistica.mensajeria.dto.request.*;
import com.ferreteriapsa.logistica.mensajeria.dto.response.*;
import com.ferreteriapsa.logistica.mensajeria.service.MensajeriaService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logistica/mensajeria")
public class MensajeriaController {
    private final MensajeriaService mensajeriaService;

    public MensajeriaController(MensajeriaService mensajeriaService){
        this.mensajeriaService = mensajeriaService;
    }

    @PreAuthorize("hasAnyRole('ALMACENERO','JEFE_DE_LINEA','ADMINISTRADOR_DE_TIENDA','ADMIN')")
    @PostMapping("/mensajes")
    public ResponseEntity<Map<String,String>> registrarMensaje(
            @RequestBody MensajeRequest request,
            @AuthenticationPrincipal CustomUserPrincipal principal){

        Long trabajadorId = principal.getTrabajadorId();
        mensajeriaService.registrarMensaje(request, trabajadorId);

        return new ResponseEntity<>(Map.of("mensaje","Mensaje enviado"),HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ALMACENERO','JEFE_DE_LINEA','ADMINISTRADOR_DE_TIENDA','ADMIN')")   
    @GetMapping("/mensajes")
    public ResponseEntity<List<MensajeResponse>> listarMensajesUsuario(
            @AuthenticationPrincipal CustomUserPrincipal principal){
        
        Long trabajadorId = principal.getTrabajadorId();
        List<MensajeResponse> mensajes = mensajeriaService.listarMensajesUsuario(trabajadorId);
        return new ResponseEntity<>(mensajes,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ALMACENERO','JEFE_DE_LINEA','ADMINISTRADOR_DE_TIENDA','ADMIN')")   
    @PatchMapping("/mensajes/{id}")
    public ResponseEntity<MensajeResponse> marcarMensajeComoLeido(@PathVariable("id") Long mensajeId){
        MensajeResponse mensaje = mensajeriaService.marcarMensajeComoLeido(mensajeId);
        return new ResponseEntity<>(mensaje,HttpStatus.OK);
    }
}
