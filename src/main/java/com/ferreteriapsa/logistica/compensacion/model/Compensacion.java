package com.ferreteriapsa.logistica.compensacion.model;

import java.math.BigDecimal;

import com.ferreteriapsa.logistica.ventas.model.Pedido;

import jakarta.persistence.*;

@Entity
@Table(name = "compensaciones")
public class Compensacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compensacion_id")
    private Long compensacionId;

    @Column(name = "dias_retraso", nullable = false)
    private int diasRetraso;

    @Column(name = "monto_compensacion", nullable = false)
    private BigDecimal montoCompensacion;

    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Compensacion() {}

    public Long getCompensacionId() { return compensacionId; }
    public void setCompensacionId(Long compensacionId) { this.compensacionId = compensacionId; }

    public int getDiasRetraso() { return diasRetraso; }
    public void setDiasRetraso(int diasRetraso) { this.diasRetraso = diasRetraso; }

    public BigDecimal getMontoCompensacion() { return montoCompensacion; }
    public void setMontoCompensacion(BigDecimal montoCompensacion) { this.montoCompensacion = montoCompensacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

}
