package com.ferreteriapsa.logistica.penalidad.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.ferreteriapsa.logistica.compra.model.OrdenCompra;

@Entity
@Table(name = "historiales_plazo_orden")
public class HistorialPlazoOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historial_id")
    private Long historialId;

    @Column(name = "fecha_plazo_anterior", nullable = false)
    private LocalDateTime fechaPlazoAnterior;

    @Column(name = "fecha_plazo_nuevo", nullable = false)
    private LocalDateTime fechaPlazoNuevo;

    @Column(name = "fecha_cambio", nullable = false)
    private LocalDateTime fechaCambio;

    @Column(nullable = false)
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "orden_compra_id", nullable = false)
    private OrdenCompra ordenCompra;

    public HistorialPlazoOrden(){}

    public Long getHistorialId() { return historialId; }
    public void setHistorialId(Long historialId) { this.historialId = historialId; }

    public LocalDateTime getFechaPlazoAnterior() { return fechaPlazoAnterior; }
    public void setFechaPlazoAnterior(LocalDateTime fechaPlazoAnterior) { this.fechaPlazoAnterior = fechaPlazoAnterior; }

    public LocalDateTime getFechaPlazoNuevo() { return fechaPlazoNuevo; }
    public void setFechaPlazoNuevo(LocalDateTime fechaPlazoNuevo) { this.fechaPlazoNuevo = fechaPlazoNuevo; }

    public LocalDateTime getFechaCambio() { return fechaCambio; }
    public void setFechaCambio(LocalDateTime fechaCambio) { this.fechaCambio = fechaCambio; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public OrdenCompra getOrdenCompra() { return ordenCompra; }
    public void setOrdenCompra(OrdenCompra ordenCompra) { this.ordenCompra = ordenCompra; }

    
}