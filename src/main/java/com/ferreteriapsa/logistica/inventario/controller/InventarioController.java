package com.ferreteriapsa.logistica.inventario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ferreteriapsa.logistica.inventario.dto.response.InventarioDTO;
import com.ferreteriapsa.logistica.inventario.service.InventarioService;

@RestController
@RequestMapping("/logistica/inventario")
public class InventarioController {
    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }
    @PreAuthorize("hasRole('JEFE_DE_LINEA')")
    @GetMapping("/productosLinea")
    public ResponseEntity<List<InventarioDTO>> listarLineaProducto() {
        List<InventarioDTO> listaProductos = inventarioService.listarInventarioLinea();
        return new ResponseEntity<>(listaProductos, HttpStatus.OK);
    }

}
