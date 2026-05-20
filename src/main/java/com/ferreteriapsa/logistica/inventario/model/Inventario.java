package com.ferreteriapsa.logistica.inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import com.ferreteriapsa.logistica.catalogo.model.Producto;
import com.ferreteriapsa.logistica.trabajador.model.Almacen;

@Entity
@Table(name = "inventarios")
public class Inventario {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventario_id")
    private Long inventarioId;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer stock;

    @Min(0)
    @Column(name = "stock_min")
    private Integer stockMin;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "rotacion", nullable = false)
    private Rotacion rotacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "almacen_id", nullable = false)
    private Almacen almacen;

    public Inventario() {}

    public Inventario(Long inventarioId, Integer stock, Integer stockMin, Rotacion rotacion, Producto producto, Almacen almacen) {
        this.inventarioId = inventarioId;
        this.stock = stock;
        this.stockMin = stockMin;
        this.rotacion = rotacion;
        this.producto = producto;
        this.almacen = almacen;
    }

    public Long getInventarioId() {
        return inventarioId;
    }

    public void setInventarioId(Long inventarioId) {
        this.inventarioId = inventarioId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStockMin() {
        return stockMin;
    }

    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    public Rotacion getRotacion() {
        return rotacion;
    }

    public void setRotacion(Rotacion rotacion) {
        this.rotacion = rotacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }
}

//Acción correcta crear una carpeta e incluirla: model/entity/... y model/enums/Rotacion.java
enum Rotacion {
    BAJA, MEDIA, ALTA
}


