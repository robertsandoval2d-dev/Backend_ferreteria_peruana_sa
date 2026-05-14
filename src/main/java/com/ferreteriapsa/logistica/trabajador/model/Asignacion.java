package com.ferreteriapsa.logistica.trabajador.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "asignaciones")
public class Asignacion {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="asignacion_id")
    private Long asignacionId;   

    @Column(name="fecha_inicio",nullable = true)
    private LocalDate fechaInicio;

    @Column(name="fecha_fin", nullable = true)
    private LocalDate fechaFin;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean activo=true;

    @ManyToOne
    @JoinColumn(name = "tienda_id", nullable = false)
    private Tienda tienda;

    @ManyToOne
    @JoinColumn(name = "trabajador_id", nullable = true)
    private Trabajador trabajador; 

    @ManyToOne
    @JoinColumn(name = "linea_producto_id", nullable = true)
    private LineaProducto lineaProducto; 

// Constructor vacío (Obligatorio para JPA)
    public Asignacion() {
    }

    // Constructor con argumentos (Útil para tus Endpoints/Services)
    public Asignacion(LocalDate fechaInicio, LocalDate fechaFin, boolean activo, Tienda tienda, Trabajador trabajador, LineaProducto lineaProducto) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
        this.tienda = tienda;
        this.trabajador = trabajador;
        this.lineaProducto = lineaProducto;
    }

    // --- GETTERS Y SETTERS ---

    public Long getAsignacionId() {
        return asignacionId;
    }

    public void setAsignacionId(Long asignacionId) {
        this.asignacionId = asignacionId;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public LineaProducto getLineaProducto() {
        return lineaProducto;
    }

    public void setLineaProducto(LineaProducto lineaProducto) {
        this.lineaProducto = lineaProducto;
    }

}
