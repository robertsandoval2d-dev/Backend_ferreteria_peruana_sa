package com.ferreteriapsa.logistica.planificacion.dto.request;

import java.time.LocalDate;

public class DetalleCronogramaRequest {

    private Long productoId;
    private Long proveedorId;
    private Integer cantidad;
    private LocalDate fechaRequerida;

    public DetalleCronogramaRequest(){}

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
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
}
