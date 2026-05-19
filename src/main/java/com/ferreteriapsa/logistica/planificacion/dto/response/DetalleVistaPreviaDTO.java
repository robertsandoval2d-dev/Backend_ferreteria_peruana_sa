package com.ferreteriapsa.logistica.planificacion.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DetalleVistaPreviaDTO {

    private String nombreLinea;
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnidad;
    
    @JsonProperty("subtotal") 
    private BigDecimal subTotal;
    
    private LocalDate fechaRequerida;

    // Constructor vacío para Jackson
    public DetalleVistaPreviaDTO() {}

    // Constructor completo para facilitar el mapeo en el Service
    public DetalleVistaPreviaDTO(String nombreLinea, Long productoId, String nombreProducto, Integer cantidad, 
                                 BigDecimal precioUnidad, LocalDate fechaRequerida) {

        this.nombreLinea = nombreLinea;                            
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
        this.fechaRequerida = fechaRequerida;
        
        // Cálculo automático del subtotal al instanciar el DTO
        if (precioUnidad != null && cantidad != null) {
            this.subTotal = precioUnidad.multiply(BigDecimal.valueOf(cantidad));
        } else {
            this.subTotal = BigDecimal.ZERO;
        }
    }

    // Getters y Setters
    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
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

    public BigDecimal getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(BigDecimal precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public LocalDate getFechaRequerida() {
        return fechaRequerida;
    }

    public void setFechaRequerida(LocalDate fechaRequerida) {
        this.fechaRequerida = fechaRequerida;
    }

    public String getNombreLinea(){
        return nombreLinea;
    }
}