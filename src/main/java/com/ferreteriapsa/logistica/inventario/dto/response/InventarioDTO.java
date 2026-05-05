package com.ferreteriapsa.logistica.inventario.dto.response;

public class InventarioDTO {
    private Long productoId;
    private String nombre;
    private Integer stockActual;
    private Integer stockMinimo;
    private String rotacion;
    private String categoria;

    public InventarioDTO() {}

    public InventarioDTO(Long productoId, String nombre, Integer stockActual, Integer stockMinimo, String rotacion, String categoria) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.rotacion = rotacion;
        this.categoria = categoria;
    }

    public Long getProductoId() {
        return productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public String getRotacion() {
        return rotacion;
    }

    public String getCategoria() {
        return categoria;
    }
}


