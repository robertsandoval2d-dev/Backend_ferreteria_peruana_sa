package com.ferreteriapsa.logistica.analisis.dto.response;

public class SaturacionZonaResponse {

    private String categoriaZona;
    private int capacidadMaxima;
    private int capacidadActual;
    private double porcentajeOcupacion;

    public SaturacionZonaResponse(String categoriaZona, int capacidadMaxima, int capacidadActual, double porcentajeOcupacion) {
        this.categoriaZona = categoriaZona;
        this.capacidadMaxima = capacidadMaxima;
        this.capacidadActual = capacidadActual;
        this.porcentajeOcupacion = porcentajeOcupacion;
    }
    
    public String getCategoriaZona() { return categoriaZona; }
    public void setCategoriaZona(String categoriaZona) { this.categoriaZona = categoriaZona; }

    public int getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(int capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }

    public int getCapacidadActual() { return capacidadActual; }
    public void setCapacidadActual(int capacidadActual) { this.capacidadActual = capacidadActual; }

    public double getPorcentajeOcupacion() { return porcentajeOcupacion; }
    public void setPorcentajeOcupacion(double porcentajeOcupacion) { this.porcentajeOcupacion = porcentajeOcupacion; }
}
