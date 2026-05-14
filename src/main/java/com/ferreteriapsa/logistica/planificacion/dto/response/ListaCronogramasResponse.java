package com.ferreteriapsa.logistica.planificacion.dto.response;

import java.util.List;

public class ListaCronogramasResponse {

    private Long cronogramaId;
    private String nombreLineaProductos;
    private List<DetalleCronogramaDTO> detallesCronograma;

    public ListaCronogramasResponse() {
    }

    public ListaCronogramasResponse(Long cronogramaId,
                                    String nombreLineaProductos,
                                    List<DetalleCronogramaDTO> detallesCronograma) {
        this.cronogramaId = cronogramaId;
        this.nombreLineaProductos = nombreLineaProductos;
        this.detallesCronograma = detallesCronograma;
    }

    public Long getCronogramaId() {
        return cronogramaId;
    }

    public void setCronogramaId(Long cronogramaId) {
        this.cronogramaId = cronogramaId;
    }

    public String getNombreLineaProductos() {
        return nombreLineaProductos;
    }

    public void setNombreLineaProductos(String nombreLineaProductos) {
        this.nombreLineaProductos = nombreLineaProductos;
    }

    public List<DetalleCronogramaDTO> getDetallesCronograma() {
        return detallesCronograma;
    }

    public void setDetallesCronograma(List<DetalleCronogramaDTO> detalles) {
        this.detallesCronograma = detalles;
    }
}