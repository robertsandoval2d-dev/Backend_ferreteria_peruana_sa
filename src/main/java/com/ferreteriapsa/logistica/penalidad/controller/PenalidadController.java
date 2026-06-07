package com.ferreteriapsa.logistica.penalidad.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ferreteriapsa.logistica.auth.config.CustomUserPrincipal;
import com.ferreteriapsa.logistica.penalidad.dto.request.*;
import com.ferreteriapsa.logistica.penalidad.dto.response.*;
import com.ferreteriapsa.logistica.penalidad.service.PenalidadService;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/logistica/penalidades")
public class PenalidadController {
    private final PenalidadService penalidadService;

    public PenalidadController(PenalidadService penalidadService){
        this.penalidadService = penalidadService;
    }

    @PreAuthorize("hasRole('ADMINISTRADOR_DE_TIENDA')")
    @PostMapping("/ordenes-compra")
    public ResponseEntity<Map<String,String>> registrarPenalidad(
        @RequestBody PenalidadRequest request){

        penalidadService.registrarPenalidad(request);

        return new ResponseEntity<>(Map.of("mensaje","Penalidad registrada"),HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR_DE_TIENDA')")
    @GetMapping("ordenes-compra/vencidas")
    public ResponseEntity<List<OrdenConRetrasoResponse>> listarOrdenesDeCompraConRetraso(
            @AuthenticationPrincipal CustomUserPrincipal principal){
            
        Long trabajadorId = principal.getTrabajadorId();
        List<OrdenConRetrasoResponse> ordenes = penalidadService.listarOrdenesCompraConRetraso(trabajadorId);
        return ResponseEntity.ok(ordenes);
    }
    
}
