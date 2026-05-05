package com.ferreteriapsa.logistica.planificacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ferreteriapsa.logistica.auth.config.CustomUserPrincipal;
import com.ferreteriapsa.logistica.planificacion.dto.request.DetalleCronogramaRequest;
import com.ferreteriapsa.logistica.planificacion.dto.response.CronogramaResponse;
import com.ferreteriapsa.logistica.planificacion.service.PlanificacionService;

@RestController
@RequestMapping("/logistica/planificacion")
public class PlanificacionController {

    private final PlanificacionService planificacionService;

    public PlanificacionController(PlanificacionService planificacionService) {
        this.planificacionService = planificacionService;
    }

    @PreAuthorize("hasRole('JEFE_DE_LINEA')")
    @PostMapping("/generarCronograma")
    public ResponseEntity<CronogramaResponse> generarCronograma(
            @AuthenticationPrincipal CustomUserPrincipal principal,
            @RequestBody List<DetalleCronogramaRequest> detalles) {
        
        Long trabajadorId = principal.getTrabajadorId();

        CronogramaResponse response = planificacionService.generarCronograma(trabajadorId,detalles);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}