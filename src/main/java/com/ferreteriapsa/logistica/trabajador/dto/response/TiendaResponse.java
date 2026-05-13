package com.ferreteriapsa.logistica.trabajador.dto.response;

import java.util.List;

public class TiendaResponse {

    private Long tiendaId;
    private String nombreTienda;
    private List<LineaProductoResponse> lineasProducto;

    public TiendaResponse(
            Long tiendaId,
            String nombreTienda,
            List<LineaProductoResponse> lineasProducto
    ) {
        this.tiendaId = tiendaId;
        this.nombreTienda = nombreTienda;
        this.lineasProducto = lineasProducto;
    }

    public Long getTiendaId() {
        return tiendaId;
    }

    public void setTiendaId(Long tiendaId) {
        this.tiendaId = tiendaId;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public List<LineaProductoResponse> getLineasProducto() {
        return lineasProducto;
    }

    public void setLineasProducto(List<LineaProductoResponse> lineasProducto) {
        this.lineasProducto = lineasProducto;
    }

}
