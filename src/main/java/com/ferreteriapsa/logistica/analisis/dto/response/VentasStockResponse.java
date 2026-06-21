package com.ferreteriapsa.logistica.analisis.dto.response;

public class VentasStockResponse {
    private String producto;
    private Long numVentas;
    private Integer stockActual;

    public VentasStockResponse(String producto, Long numVentas, Integer stockActual) {
        this.producto = producto;
        this.numVentas = numVentas;
        this.stockActual = stockActual;
    }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public Long getNumVentas() { return numVentas; }
    public void setNumVentas(Long numVentas) { this.numVentas = numVentas; }

    public Integer getStockActual() { return stockActual; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }
}
