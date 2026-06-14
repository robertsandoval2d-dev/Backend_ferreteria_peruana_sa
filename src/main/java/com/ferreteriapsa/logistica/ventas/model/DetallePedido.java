package com.ferreteriapsa.logistica.ventas.model;

import java.math.BigDecimal;

import com.ferreteriapsa.logistica.catalogo.model.Producto;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_pedidos")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalle_pedido_id")
    private Long detallePedidoId;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "precio_unidad", nullable = false)
    private BigDecimal precioUnidad;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public DetallePedido() {}

    public Long getDetallePedidoId() { return detallePedidoId; }
    public void setDetallePedidoId(Long detallePedidoId) { this.detallePedidoId = detallePedidoId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnidad() { return precioUnidad; }
    public void setPrecioUnidad(BigDecimal precioUnidad) { this.precioUnidad = precioUnidad; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }  
      
}
