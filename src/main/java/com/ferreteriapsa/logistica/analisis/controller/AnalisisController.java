package com.ferreteriapsa.logistica.analisis.controller;

import com.ferreteriapsa.logistica.analisis.dto.response.*;
import com.ferreteriapsa.logistica.analisis.service.AnalisisService;
import com.ferreteriapsa.logistica.auth.config.CustomUserPrincipal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logistica/analisis")
public class AnalisisController {

    private final AnalisisService analisisService;

    public AnalisisController(AnalisisService analisisService) {
        this.analisisService = analisisService;
    }

    @PreAuthorize("hasRole('JEFE_DE_LINEA')")
    @GetMapping("/zonas-almacen/porcentaje-abastecimiento")
    public ResponseEntity<List<SaturacionZonaResponse>> obtenerPorcentajeAbastecimiento(
            @AuthenticationPrincipal CustomUserPrincipal principal) {
        
        Long trabajadorId = principal.getTrabajadorId();

        List<SaturacionZonaResponse> saturacionZonas = analisisService.obtenerSaturacionZonas(trabajadorId);

        return ResponseEntity.ok(saturacionZonas);
    }   

    @PreAuthorize("hasRole('JEFE_DE_LINEA')")
    @GetMapping("/productos-linea/valor-financiero")
    public ResponseEntity<List<ValorInmovilizadoResponse>> obtenerValorFinancieroLineas(
            @AuthenticationPrincipal CustomUserPrincipal principal) {
        
        Long trabajadorId = principal.getTrabajadorId();

        List<ValorInmovilizadoResponse> valorInmovilizado = analisisService.obtenerCostoInmovilizadoPorTrabajador(trabajadorId);

        return ResponseEntity.ok(valorInmovilizado);
    }

    @PreAuthorize("hasRole('JEFE_DE_LINEA')")
    @GetMapping("/productos-linea/ventas-stock")
    public ResponseEntity<List<VentasStockResponse>> obtenerVentasYStock(
            @AuthenticationPrincipal CustomUserPrincipal principal) {
        
        Long trabajadorId = principal.getTrabajadorId();

        List<VentasStockResponse> relacionVentasStock = analisisService.obtenerRelacionVentasStock(trabajadorId);

        return ResponseEntity.ok(relacionVentasStock);
    }
}
