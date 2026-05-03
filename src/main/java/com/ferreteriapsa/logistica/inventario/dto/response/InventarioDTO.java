package com.ferreteriapsa.logistica.inventario.dto.response;

public class InventarioDTO {
    private Long id;
    private String nombre;
    private Integer stockActual;
    private Integer stockMinimo;
    private String rotacion;
    private String categoria;

    public InventarioDTO() {}

    public InventarioDTO(Long id, String nombre, Integer stockActual, Integer stockMinimo, String rotacion, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.rotacion = rotacion;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
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


