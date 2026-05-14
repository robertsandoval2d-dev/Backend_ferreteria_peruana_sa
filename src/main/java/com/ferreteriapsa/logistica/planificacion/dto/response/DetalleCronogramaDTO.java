package com.ferreteriapsa.logistica.planificacion.dto.response;

import java.time.LocalDate;

public class DetalleCronogramaDTO {

    private String nombreProveedor;
    private String nombreProducto;
    private Integer cantidad;
    private LocalDate fechaRequerida;

    public DetalleCronogramaDTO() {
    }

    public DetalleCronogramaDTO(String nombreProveedor,
                                String nombreProducto,
                                Integer cantidad,
                                LocalDate fechaRequerida) {
        this.nombreProveedor = nombreProveedor;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.fechaRequerida = fechaRequerida;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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
