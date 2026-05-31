package com.ferreteriapsa.logistica.compra.dto.response;

import java.time.LocalDateTime;

public class OrdenCompraSimpleResponse {
    private Long ordenCompraId;
    private String nombreProveedor;
    private LocalDateTime fechaCreacion;

    public OrdenCompraSimpleResponse() {
    }

    public OrdenCompraSimpleResponse(
            Long ordenCompraId,
            String nombreProveedor,
            LocalDateTime fechaCreacion) {
        this.ordenCompraId = ordenCompraId;
        this.nombreProveedor = nombreProveedor;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getOrdenCompraId() {
        return ordenCompraId;
    }

    public void setOrdenCompraId(Long ordenCompraId) {
        this.ordenCompraId = ordenCompraId;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
