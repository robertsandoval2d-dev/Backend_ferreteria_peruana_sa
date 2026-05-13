package com.ferreteriapsa.logistica.trabajador.dto.response;

public class LineaProductoResponse {

    private Long lineaId;
    private String nombreLinea;

    public LineaProductoResponse(Long lineaId, String nombreLinea) {
        this.lineaId = lineaId;
        this.nombreLinea = nombreLinea;
    }
    public Long getLineaId(){
        return lineaId;
    }
    public void setLineaId(Long lineaId){
        this.lineaId = lineaId;
    }
    public String getNombreLinea(){
        return nombreLinea;
    }
    public void setNombreLinea(String nombreLinea){
        this.nombreLinea = nombreLinea;
    }
}