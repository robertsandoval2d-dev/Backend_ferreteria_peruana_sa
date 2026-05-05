package com.ferreteriapsa.logistica.catalogo.model;

import jakarta.persistence.*;

import com.ferreteriapsa.logistica.trabajador.model.LineaProducto;

@Entity
@Table(name = "productos")
public class Producto {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long productoId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String categoria;

    @ManyToOne(fetch = FetchType.LAZY) //Hace selects necesarios, primero la tabla si se solicita mas info la siguiente
    @JoinColumn(name = "linea_producto_id")
    private LineaProducto lineaProducto;

    // constructores

    public Producto() {}

    public Producto(String nombre, String descripcion, String categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    //getters and setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getProductoId() {
        return productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }
}

