package com.ferreteriapsa.logistica.compra.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.ferreteriapsa.logistica.catalogo.model.Proveedor;
import com.ferreteriapsa.logistica.trabajador.model.Tienda;
import com.ferreteriapsa.logistica.trabajador.model.Trabajador;

import jakarta.persistence.*;


@Entity
@Table(name = "ordenes_compra")
public class OrdenCompra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orden_compra_id")
    private Long ordenCompraId;

    @Column(name = "plazo_fecha_maximo", nullable = false)
    private LocalDateTime plazoFechaMaximo;

    @Column(name = "monto_total_calculado", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoTotalCalculado;
    
    @Column(nullable = false)
    private String estado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_entrega", nullable = true) 
    private LocalDateTime fechaEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrador_id", nullable = false)
    private Trabajador administrador;

    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;

    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleOrdenCompra> detalles;

    // Constructor vacío
    public OrdenCompra() {}

    // Getters y Setters
    public Long getOrdenCompraId() { return ordenCompraId; }
    public void setOrdenCompraId(Long ordenCompraId) { this.ordenCompraId = ordenCompraId; }

    public LocalDateTime getPlazoFechaMaximo() { return plazoFechaMaximo; }
    public void setPlazoFechaMaximo(LocalDateTime plazoFechaMaximo) { this.plazoFechaMaximo = plazoFechaMaximo; }

    public BigDecimal getMontoTotalCalculado() { return montoTotalCalculado; }
    public void setMontoTotalCalculado(BigDecimal montoTotalCalculado) { this.montoTotalCalculado = montoTotalCalculado; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public Proveedor getProveedor() { return proveedor; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }

    public List<DetalleOrdenCompra> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleOrdenCompra> detalles) { this.detalles = detalles; }

    public Trabajador getAdministrador() { return administrador; }
    public void setAdministrador(Trabajador administrador) { this.administrador = administrador; }

    public Tienda getTienda(){ return tienda; } 
    public void setTienda(Tienda tienda){ this.tienda = tienda; }
}