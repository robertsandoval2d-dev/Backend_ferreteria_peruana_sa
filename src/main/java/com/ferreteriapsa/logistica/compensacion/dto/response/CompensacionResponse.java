package com.ferreteriapsa.logistica.compensacion.dto.response;

import java.math.BigDecimal;

public class CompensacionResponse {
    private Long compensacionId;
    private int diasRetraso;
    private BigDecimal montoCompensacion;
    private BigDecimal montoTotalPedido;
    private String estado;  

    public CompensacionResponse() {}

    public Long getCompensacionId() { return compensacionId; }
    public void setCompensacionId(Long compensacionId) { this.compensacionId = compensacionId; }

    public int getDiasRetraso() { return diasRetraso; }
    public void setDiasRetraso(int diasRetraso) { this.diasRetraso = diasRetraso; }

    public BigDecimal getMontoCompensacion() { return montoCompensacion; }
    public void setMontoCompensacion(BigDecimal montoCompensacion) { this.montoCompensacion = montoCompensacion; }

    public BigDecimal getMontoTotalPedido() { return montoTotalPedido; }
    public void setMontoTotalPedido(BigDecimal montoTotalPedido) { this.montoTotalPedido = montoTotalPedido; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
