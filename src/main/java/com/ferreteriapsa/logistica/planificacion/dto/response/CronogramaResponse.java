package com.ferreteriapsa.logistica.planificacion.dto.response;

import java.time.LocalDateTime;

public class CronogramaResponse {

    private Long cronogramaId;
    private LocalDateTime fechaCreacion;

    public Long getCronogramaId() {
        return cronogramaId;
    }

    public void setCronogramaId(Long cronogramaId) {
        this.cronogramaId = cronogramaId;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
