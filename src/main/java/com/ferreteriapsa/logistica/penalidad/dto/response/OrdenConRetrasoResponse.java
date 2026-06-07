package com.ferreteriapsa.logistica.penalidad.dto.response;

import java.time.LocalDateTime;

public class OrdenConRetrasoResponse {
    private Long ordenCompraId;
    private String proveedor;
    private LocalDateTime fechaEntrega;
    private LocalDateTime fechaLimite;
    private String estado;

    public OrdenConRetrasoResponse (){}

    public Long getOrdenCompraId() { return ordenCompraId; }
    public void setOrdenCompraId(Long ordenCompraId) { this.ordenCompraId = ordenCompraId; }

    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }

    public LocalDateTime getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public LocalDateTime getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDateTime fechaLimite) { this.fechaLimite = fechaLimite; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
}
