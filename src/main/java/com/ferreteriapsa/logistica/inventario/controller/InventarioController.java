package com.ferreteriapsa.logistica.inventario.controller;

import java.util.List;
import java.util.Map;

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
    @PostMapping("/ordenes-compra/recepcion")
    public ResponseEntity<Map<String,String>> registrarRecepcion(
            @RequestBody RegistroMercaderiaRequest request,
            @AuthenticationPrincipal CustomUserPrincipal principal){

        Long trabajadorId = principal.getTrabajadorId();
        inventarioService.regitrarOrdenCompra(request, trabajadorId);

        return ResponseEntity.ok(
                Map.of("mensaje", "Mercadería recepcionada correctamente")
        );
    }
}
