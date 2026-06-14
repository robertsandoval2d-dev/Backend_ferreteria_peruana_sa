package com.ferreteriapsa.logistica.ventas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ferreteriapsa.logistica.auth.config.CustomUserPrincipal;
import com.ferreteriapsa.logistica.ventas.dto.response.*;
import com.ferreteriapsa.logistica.ventas.service.VentasService;

@RestController
@RequestMapping("/logistica/ventas")
public class VentasController {
    
    private final VentasService ventasService;

    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    @PreAuthorize("hasRole('JEFE_DE_LINEA')")
    @GetMapping("/clientes/afectados-retraso")
    public ResponseEntity<List<PedidoRetrasado>> listarPedidosRetrasados(
            @AuthenticationPrincipal CustomUserPrincipal principal) {
            
        Long trabajadorId = principal.getTrabajadorId();
        
        List<PedidoRetrasado> pedidos = ventasService.listarPedidosRetrasados(trabajadorId);
        
        return ResponseEntity.ok(pedidos);
    }
    
}
