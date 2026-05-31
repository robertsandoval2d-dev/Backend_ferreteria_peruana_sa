package com.ferreteriapsa.logistica.compra.dto.response;

public class ProductoDTO {
    private Long productoId;
    private String nombreProducto;
    private String nombreLinea;
    private Integer cantidad;


    public ProductoDTO() {
    }

    public ProductoDTO(Long productoId, String nombreProducto, String nombreLinea, Integer cantidad) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.nombreLinea = nombreLinea;
        this.cantidad = cantidad;
    }

    public Long getProductoId() {
        return productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}

