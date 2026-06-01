package com.ferreteriapsa.logistica.compra.dto.response;

import java.time.LocalDateTime;

public class OrdenCompraSimpleResponse {
    private Long ordenCompraId;
    private String nombreProveedor;
    private LocalDateTime fechaEntrega;
    private LocalDateTime fechaPlazoMaximo;

    public OrdenCompraSimpleResponse() {
    }

    public OrdenCompraSimpleResponse(
            Long ordenCompraId,
            String nombreProveedor,
            LocalDateTime fechaEntrega,
            LocalDateTime fechaPlazoMaximo) {
        this.ordenCompraId = ordenCompraId;
        this.nombreProveedor = nombreProveedor;
        this.fechaEntrega = fechaEntrega;
        this.fechaPlazoMaximo = fechaPlazoMaximo;
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

    public LocalDateTime getFechaPlazoMaximo() {
        return fechaPlazoMaximo;
    }

    public void setFechaPlazoMaximo(LocalDateTime fechaPlazoMaximo) {
        this.fechaPlazoMaximo = fechaPlazoMaximo;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}
