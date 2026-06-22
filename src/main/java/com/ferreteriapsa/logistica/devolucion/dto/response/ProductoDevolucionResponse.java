package com.ferreteriapsa.logistica.devolucion.dto.response;

public class ProductoDevolucionResponse {
    private Long idProducto;
    private String producto;
    private Integer stock;
    private String rotacion;
    private Integer ventas;
    private Integer stockActual;
    private Integer diasSinVentas;
    private Double valorStock;

    public ProductoDevolucionResponse() {}

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getRotacion() {
        return rotacion;
    }

    public void setRotacion(String rotacion) {
        this.rotacion = rotacion;
    }

    public Integer getVentas() {
        return ventas;
    }

    public void setVentas(Integer ventas) {
        this.ventas = ventas;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getDiasSinVentas() {
        return diasSinVentas;
    }

    public void setDiasSinVentas(Integer diasSinVentas) {
        this.diasSinVentas = diasSinVentas;
    }

    public Double getValorStock() {
        return valorStock;
    }

    public void setValorStock(Double valorStock) {
        this.valorStock = valorStock;
    }
}
