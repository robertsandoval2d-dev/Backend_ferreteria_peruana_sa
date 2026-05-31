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

import java.util.List;

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

    @PreAuthorize("hasAnyRole('ALMACENERO','JEFE_DE_LINEA')")
    @GetMapping("/ordenes-compra")
    public ResponseEntity<List<OrdenesCompraResponse>> listarOrdenes(
            @AuthenticationPrincipal CustomUserPrincipal principal,
            @RequestParam(required = false) Long ordenId){

        Long trabajadorId = principal.getTrabajadorId();
        List<OrdenesCompraResponse> listaOrdenesCompra = compraService.listarOrdenesPorTiendaYProveedor(
                trabajadorId,
                ordenId
        );
        return ResponseEntity.ok(listaOrdenesCompra);
    }

    @PreAuthorize("hasRole('JEFE_DE_LINEA')")
    @GetMapping("/ordenes-compra/simple")
    public ResponseEntity<List<OrdenCompraSimpleResponse>> listarOrdenesSimple(
            @AuthenticationPrincipal CustomUserPrincipal principal){

        Long trabajadorId = principal.getTrabajadorId();
        List<OrdenCompraSimpleResponse> listaOrdenesCompraSimple = compraService.listarOrdenesCompraSimple(trabajadorId);

        return ResponseEntity.ok(listaOrdenesCompraSimple);
    }
    
}
