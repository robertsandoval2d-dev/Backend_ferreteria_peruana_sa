package com.ferreteriapsa.logistica.compra.models;

import java.math.BigDecimal;

import com.ferreteriapsa.logistica.catalogo.model.Producto;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_ordenes_compra")
public class DetalleOrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalle_oc_id")
    private Long detalleOcId;

    @Column(name = "nombre_linea", nullable = false)
    private String nombreLinea;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unidad", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnidad;

    @ManyToOne
    @JoinColumn(name = "orden_compra_id", nullable = false)
    private OrdenCompra ordenCompra;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public DetalleOrdenCompra(){

    }
    // Métodos para obtener el subtotal sin guardarlo en la BD
    public BigDecimal getSubTotal() {
        if (cantidad == null || precioUnidad == null) return BigDecimal.ZERO;
        return precioUnidad.multiply(new BigDecimal(cantidad));
    }

    // Getters y Setters
    public Long getDetalleOcId() { return detalleOcId; }
    public void setDetalleOcId(Long detalleOcId) { this.detalleOcId = detalleOcId; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnidad() { return precioUnidad; }
    public void setPrecioUnidad(BigDecimal precioUnidad) { this.precioUnidad = precioUnidad; }

    public OrdenCompra getOrdenCompra() { return ordenCompra; }
    public void setOrdenCompra(OrdenCompra ordenCompra) { this.ordenCompra = ordenCompra; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public String getNombreLinea(){ return nombreLinea; }
    public void setNombreLinea(String nombreLinea){ this.nombreLinea = nombreLinea; }
}