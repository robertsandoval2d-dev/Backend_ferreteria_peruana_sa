package com.ferreteriapsa.logistica.ventas.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ferreteriapsa.logistica.trabajador.model.Tienda;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_entrega_maxima", nullable = false)
    private LocalDateTime fechaEntregaMaxima;

    @Column(name = "fecha_entrega", nullable = true)
    private LocalDateTime fechaEntrega;

    @Column(name = "monto_total", nullable = false)
    private BigDecimal montoTotal;

    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;

    public Pedido() {}

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getFechaEntregaMaxima() { return fechaEntregaMaxima; }
    public void setFechaEntregaMaxima(LocalDateTime fechaEntregaMaxima) { this.fechaEntregaMaxima = fechaEntregaMaxima; }

    public LocalDateTime getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Tienda getTienda() { return tienda; }
    public void setTienda(Tienda tienda) { this.tienda = tienda; } 
       
}
