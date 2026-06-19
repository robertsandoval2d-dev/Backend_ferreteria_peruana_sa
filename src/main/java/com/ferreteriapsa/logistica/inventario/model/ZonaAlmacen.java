package com.ferreteriapsa.logistica.inventario.model;

import com.ferreteriapsa.logistica.trabajador.model.Almacen;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "zonas_almacen")
public class ZonaAlmacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zona_almacen_id")
    private Long zonaAlmacenId;

    @Column(nullable = false)
    private String categoria;

    @Min(0)
    @Column(name = "capacidad_maxima", nullable = false)
    private int capacidadMaxima;

    @Min(0)
    @Column(name = "capacidad_actual", nullable = false)
    private int capacidadActual;

    @ManyToOne
    @JoinColumn(name = "almacen_id")
    private Almacen almacen;

    public ZonaAlmacen() {}

    public Long getZonaAlmacenId() { return zonaAlmacenId; }
    public void setZonaAlmacenId(Long zonaAlmacenId) { this.zonaAlmacenId = zonaAlmacenId; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(int capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }

    public int getCapacidadActual() { return capacidadActual; }
    public void setCapacidadActual(int capacidadActual) { this.capacidadActual = capacidadActual; }

    public Almacen getAlmacen() { return almacen; }
    public void setAlmacen(Almacen almacen) { this.almacen = almacen; }

}
