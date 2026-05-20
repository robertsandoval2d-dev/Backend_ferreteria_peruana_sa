package com.ferreteriapsa.logistica.inventario.controller;

import java.util.List;

import com.ferreteriapsa.logistica.inventario.dto.response.OrdenesCompraResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ferreteriapsa.logistica.auth.config.CustomUserPrincipal;

import com.ferreteriapsa.logistica.inventario.dto.response.InventarioDTO;
import com.ferreteriapsa.logistica.inventario.service.InventarioService;
import com.ferreteriapsa.logistica.inventario.dto.request.RegistroMercaderiaRequest;

@RestController
@RequestMapping("/logistica/inventario")
public class InventarioController {
    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }
    @PreAuthorize("hasRole('JEFE_DE_LINEA')")
    @GetMapping("/productos-linea")
    public ResponseEntity<List<InventarioDTO>> listarLineaProducto(
            @AuthenticationPrincipal CustomUserPrincipal principal) {

        Long trabajadorId = principal.getTrabajadorId();

        List<InventarioDTO> listaProductos = inventarioService.listarInventarioLinea(trabajadorId);
        return new ResponseEntity<>(listaProductos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ALMACENERO')")
    @GetMapping("/ordenes-compra")
    public ResponseEntity<List<OrdenesCompraResponse>> listarOrdenes(
            @AuthenticationPrincipal CustomUserPrincipal principal,
            @RequestParam(required = false) Long proveedorId){

        Long trabajadorId = principal.getTrabajadorId();
        List<OrdenesCompraResponse> listaOrdenesCompra = inventarioService.listarOrdenesPorTiendaYProveedor(
                trabajadorId,
                proveedorId
        );
        return ResponseEntity.ok(listaOrdenesCompra);
    }

    @PreAuthorize("hasRole('ALMACENERO')")
    @PostMapping("/ordenes-compra/recepcion")
    public ResponseEntity<String> registrarRecepcion(
            @RequestBody RegistroMercaderiaRequest request){

        inventarioService.regitrarOrdenCompra(request);

        return ResponseEntity.ok(
                "Mercadería recepcionada correctamente"
        );
    }
}
