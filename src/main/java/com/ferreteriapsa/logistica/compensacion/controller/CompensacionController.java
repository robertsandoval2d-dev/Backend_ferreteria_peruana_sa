package com.ferreteriapsa.logistica.compensacion.controller;

import java.util.Map;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferreteriapsa.logistica.auth.config.CustomUserPrincipal;
import com.ferreteriapsa.logistica.compensacion.dto.request.CompensacionRequest;
import com.ferreteriapsa.logistica.compensacion.dto.response.CompensacionResponse;
import com.ferreteriapsa.logistica.compensacion.service.CompensacionService;

@RestController
@RequestMapping("/logistica/compensacion")
public class CompensacionController {
    
    private final CompensacionService compensacionService;

    public CompensacionController(CompensacionService compensacionService) {
        this.compensacionService = compensacionService;
    }

    @PreAuthorize("hasRole('JEFE_DE_LINEA')") 
    @PostMapping
    public ResponseEntity<Map<String, String>> registrarCompensacion(
            @RequestBody CompensacionRequest request) {
            
        compensacionService.registrarCompensacion(request);

        return new ResponseEntity<>(
            Map.of("mensaje", "Compensación registrada exitosamente"), 
            HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasRole('ADMINISTRADOR_DE_TIENDA')")
    @GetMapping("/pendientes")
    public ResponseEntity<List<CompensacionResponse>> listarCompensacionesPendientes(
            @AuthenticationPrincipal CustomUserPrincipal principal) {
            
        Long trabajadorId = principal.getTrabajadorId();
        
        List<CompensacionResponse> pendientes = compensacionService.listarCompensacionesPendientes(trabajadorId);
        
        return ResponseEntity.ok(pendientes);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR_DE_TIENDA')")
    @PatchMapping("/aprobar/{id}")
    public ResponseEntity<CompensacionResponse> aprobarCompensacion(
            @PathVariable("id") Long compensacionId) {
            
        CompensacionResponse response = compensacionService.aprobarCompensacion(compensacionId);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR_DE_TIENDA')")
    @PatchMapping("/rechazar/{id}")
    public ResponseEntity<CompensacionResponse> rechazarCompensacion(
            @PathVariable("id") Long compensacionId) {
            
        CompensacionResponse response = compensacionService.rechazarCompensacion(compensacionId);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
