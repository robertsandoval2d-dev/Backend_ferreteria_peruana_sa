package com.ferreteriapsa.logistica.catalogo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ferreteriapsa.logistica.catalogo.dto.response.CatalogoResponse;
import com.ferreteriapsa.logistica.catalogo.service.CatalogoService;
import com.ferreteriapsa.logistica.auth.config.CustomUserPrincipal;

@RestController
@RequestMapping("/logistica/catalogo")
public class CatalogoController {

    private final CatalogoService catalogoService;

    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @PreAuthorize("hasRole('JEFE_DE_LINEA')")
    @GetMapping("/productosLinea")
    public ResponseEntity<List<CatalogoResponse>> listarCatalogoLinea(
            @AuthenticationPrincipal CustomUserPrincipal principal) {

        Long trabajadorId = principal.getTrabajadorId();

        List<CatalogoResponse> catalogo =
                catalogoService.obtenerCatalogo(trabajadorId);

        return new ResponseEntity<>(catalogo, HttpStatus.OK);
    }
}