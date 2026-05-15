package com.ferreteriapsa.logistica.compra.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrdenCompraRequest {

    private LocalDateTime plazoFechaMaximo;
    private Long proveedorId;
    private BigDecimal montoTotalCalculado;
    private List<DetalleOrdenCompraDTO> detalles;

    // Getters y Setters
    public LocalDateTime getPlazoFechaMaximo() { return plazoFechaMaximo; }
    public void setPlazoFechaMaximo(LocalDateTime plazoFechaMaximo) { this.plazoFechaMaximo = plazoFechaMaximo; }

    public Long getProveedorId() { return proveedorId; }
    public void setProveedorId(Long proveedorId) { this.proveedorId = proveedorId; }

    public BigDecimal getMontoTotalCalculado() { return montoTotalCalculado; }
    public void setMontoTotalCalculado(BigDecimal montoTotalCalculado) { this.montoTotalCalculado = montoTotalCalculado; }

    public List<DetalleOrdenCompraDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleOrdenCompraDTO> detalles) { this.detalles = detalles; }
}