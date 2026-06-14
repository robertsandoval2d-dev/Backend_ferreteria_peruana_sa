package com.ferreteriapsa.logistica.compensacion.dto.request;

import java.math.BigDecimal;

public class CompensacionRequest {
    private Long pedidoId;
    private int diasRetraso;
    private BigDecimal montoCompensacion;

    public CompensacionRequest() {}

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public int getDiasRetraso() { return diasRetraso; }
    public void setDiasRetraso(int diasRetraso) { this.diasRetraso = diasRetraso; }

    public BigDecimal getMontoCompensacion() { return montoCompensacion; }
    public void setMontoCompensacion(BigDecimal montoCompensacion) { this.montoCompensacion = montoCompensacion; }
}
