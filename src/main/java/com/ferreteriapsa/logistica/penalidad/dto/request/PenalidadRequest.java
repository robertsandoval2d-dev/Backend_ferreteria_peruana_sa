package com.ferreteriapsa.logistica.penalidad.dto.request;

import java.math.BigDecimal;

public class PenalidadRequest {
    private Long ordenCompraId;
    private int diasRetraso;
    private BigDecimal montoPenalidad;

    public PenalidadRequest(){}

    public void setOrdenCompraId (Long ordenCompraId) { this.ordenCompraId = ordenCompraId; }
    public Long getOrdenCompraId() { return ordenCompraId; }

    public void setDiasRetraso(int diasRetraso) { this.diasRetraso = diasRetraso; }
    public int getDiasRetraso() { return diasRetraso; }

    public void setMontoPenalidad(BigDecimal montoPenalidad){ this.montoPenalidad = montoPenalidad; }
    public BigDecimal getMontoPenalidad() { return montoPenalidad; }
}
