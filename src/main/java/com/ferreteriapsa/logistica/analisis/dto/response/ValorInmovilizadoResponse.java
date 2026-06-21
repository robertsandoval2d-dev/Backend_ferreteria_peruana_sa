package com.ferreteriapsa.logistica.analisis.dto.response;

import java.math.BigDecimal;

public class ValorInmovilizadoResponse {
    private String producto;
    private BigDecimal valorMonetario; 
    private String rotacion;
    private String categoria;

    public ValorInmovilizadoResponse(String producto, BigDecimal valorMonetario, String rotacion, String categoria) {
        this.producto = producto;
        this.valorMonetario = valorMonetario;
        this.rotacion = rotacion;
        this.categoria = categoria;
    }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public BigDecimal getValorMonetario() { return valorMonetario; }
    public void setValorMonetario(BigDecimal valorMonetario) { this.valorMonetario = valorMonetario; }

    public String getRotacion() { return rotacion; }
    public void setRotacion(String rotacion) { this.rotacion = rotacion; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}