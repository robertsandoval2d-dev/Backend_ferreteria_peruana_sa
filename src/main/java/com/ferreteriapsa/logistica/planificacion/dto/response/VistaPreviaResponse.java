package com.ferreteriapsa.logistica.planificacion.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class VistaPreviaResponse {

    private Long proveedorId;
    private String nombreProveedor;
    private BigDecimal montoTotalCalculado;
    private List<DetalleVistaPreviaDTO> detalles;

    // Constructores
    public VistaPreviaResponse() {}

    public VistaPreviaResponse(Long proveedorId, String nombreProveedor, BigDecimal montoTotalCalculado, List<DetalleVistaPreviaDTO> detalles) {
        this.proveedorId = proveedorId;
        this.nombreProveedor = nombreProveedor;
        this.montoTotalCalculado = montoTotalCalculado;
        this.detalles = detalles;
    }

    // Getters y Setters
    public Long getProveedorId() { return proveedorId; }
    public void setProveedorId(Long proveedorId) { this.proveedorId = proveedorId; }

    public String getNombreProveedor() { return nombreProveedor; }
    public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }

    public BigDecimal getMontoTotalCalculado() { return montoTotalCalculado; }
    public void setMontoTotalCalculado(BigDecimal montoTotalCalculado) { this.montoTotalCalculado = montoTotalCalculado; }

    public List<DetalleVistaPreviaDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVistaPreviaDTO> detalles) { this.detalles = detalles; }

}
