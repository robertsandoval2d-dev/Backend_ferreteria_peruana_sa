package com.ferreteriapsa.logistica.catalogo.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(
    name = "productos_proveedores",
    uniqueConstraints = @UniqueConstraint(columnNames = {"producto_id", "proveedor_id"})
)
public class ProductoProveedor {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_proveedor_id")
    private Long productoProveedorId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    // constructores
    public ProductoProveedor() {}

    public ProductoProveedor(Producto producto, Proveedor proveedor, BigDecimal precio) {
        this.producto = producto;
        this.proveedor = proveedor;
        this.precio = precio;
    }

    // getters and setters
    public Long getProductoProveedorId() {
        return productoProveedorId;
    }

    public void setProductoProveedorId(Long productoProveedorId) {
        this.productoProveedorId = productoProveedorId;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public BigDecimal getPrecio(){
        return precio;
    }

    public void setprecio(BigDecimal precio){
        this.precio = precio;
    }
}
