package com.ferreteriapsa.logistica.planificacion.model;

import java.time.LocalDate;

import com.ferreteriapsa.logistica.catalogo.model.Producto;
import com.ferreteriapsa.logistica.catalogo.model.Proveedor;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_cronogramas")
public class DetalleCronograma {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalle_cronograma_id")
    private Long detalleCronogramaId;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name="fecha_requerida", nullable = false)
    private LocalDate fechaRequerida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cronograma_id", nullable = false)
    private Cronograma cronograma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    // constructores
    public DetalleCronograma(){}

    public DetalleCronograma(Integer cantidad, LocalDate fechaRequerida){
        this.cantidad = cantidad;
        this.fechaRequerida = fechaRequerida;
    }

    //getters and setters
    public Long getDetalleCronogramaId() {
        return detalleCronogramaId;
    }

    public void setDetalleCronogramaId(Long detalleCronogramaId) {
        this.detalleCronogramaId = detalleCronogramaId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaRequerida() {
        return fechaRequerida;
    }

    public void setFechaRequerida(LocalDate fechaRequerida) {
        this.fechaRequerida = fechaRequerida;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Cronograma getCronograma(){
        return cronograma;
    }

    public void setCronograma(Cronograma cronograma){
        this.cronograma = cronograma;
    }
}
