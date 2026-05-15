package com.ferreteriapsa.logistica.compra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ferreteriapsa.logistica.auth.config.CustomUserPrincipal;
import com.ferreteriapsa.logistica.compra.dto.request.*;
import com.ferreteriapsa.logistica.compra.dto.response.*;
import com.ferreteriapsa.logistica.compra.service.CompraService;

@RestController
@RequestMapping("/logistica/compras")
public class CompraController {
    private final CompraService compraService;

    public CompraController(CompraService compraService){
        this.compraService = compraService;
    }

    @PreAuthorize("hasRole('ADMINISTRADOR_DE_TIENDA')")
    @PostMapping("/ordenes-compra")
    public ResponseEntity<OrdenCompraResponse> generarOrdenCompra(
        @AuthenticationPrincipal CustomUserPrincipal principal,
        @RequestBody OrdenCompraRequest request){

        Long trabajadorId = principal.getTrabajadorId();
        OrdenCompraResponse response = compraService.generarOrdenCompra(request, trabajadorId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    
}
