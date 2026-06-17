package com.ferreteriapsa.logistica.ventas.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoRetrasadoResponse {
    private Long clienteId;
    private String nombreCliente;
    private Long pedidoId;
    private LocalDateTime fechaMaximaEntrega;
    private LocalDateTime fechaEntrega;
    private int diasRetraso;
    private BigDecimal montoTotalPedido;
    private String estado;

    public PedidoRetrasadoResponse() {}

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public LocalDateTime getFechaMaximaEntrega() { return fechaMaximaEntrega; }
    public void setFechaMaximaEntrega(LocalDateTime fechaMaximaEntrega) { this.fechaMaximaEntrega = fechaMaximaEntrega; }

    public LocalDateTime getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public int getDiasRetraso() { return diasRetraso; }
    public void setDiasRetraso(int diasRetraso) { this.diasRetraso = diasRetraso; }

    public BigDecimal getMontoTotalPedido() { return montoTotalPedido; }
    public void setMontoTotalPedido(BigDecimal montoTotalPedido) { this.montoTotalPedido = montoTotalPedido; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
