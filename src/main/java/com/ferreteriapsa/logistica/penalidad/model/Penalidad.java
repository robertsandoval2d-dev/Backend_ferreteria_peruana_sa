package com.ferreteriapsa.logistica.penalidad.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

import com.ferreteriapsa.logistica.compra.model.OrdenCompra;

@Entity
@Table(name = "penalidades")
public class Penalidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penalidad_id")
    private Long penalidadId;

    @Column(name="dias_retraso", nullable = false)
    private int diasRetraso;

    @Column(name="monto_penalidad", nullable = false)
    private BigDecimal montoPenalidad;

    @Column(name="estado_pago", nullable = false)
    private String estadoPago;

    @ManyToOne
    @JoinColumn(name = "orden_compra_id", nullable = false)
    private OrdenCompra ordenCompra;

    public Penalidad() {}

    public Long getPenalidadId() { return penalidadId; }
    public void setPenalidadId(Long penalidadId) { this.penalidadId = penalidadId; }

    public int getDiasRetraso() { return diasRetraso; }
    public void setDiasRetraso(int diasRetraso) { this.diasRetraso = diasRetraso; }

    public BigDecimal getMontoPenalidad() { return montoPenalidad; }
    public void setMontoPenalidad(BigDecimal montoPenalidad) { this.montoPenalidad = montoPenalidad; }

    public String getEstadoPago() { return estadoPago; }
    public void setEstadoPago(String estadoPago) { this.estadoPago = estadoPago; }

    public OrdenCompra getOrdenCompra() { return ordenCompra; }
    public void setOrdenCompra(OrdenCompra ordenCompra) { this.ordenCompra = ordenCompra; }

}
