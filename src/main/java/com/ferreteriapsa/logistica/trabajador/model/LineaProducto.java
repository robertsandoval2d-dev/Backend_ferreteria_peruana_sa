package com.ferreteriapsa.logistica.trabajador.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lineas_producto")
public class LineaProducto {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="linea_producto_id")
    private Long lineaProductoId;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;

    @OneToOne
    @JoinColumn(name = "jefe_de_linea_id")
    private Trabajador jefeDeLinea;

    @ManyToOne
    @JoinColumn(name = "tienda_id", nullable = false)
    private Tienda tienda;

    // constructores
    public LineaProducto() {}

    public LineaProducto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // getters y setters

    public Long getId() {
        return lineaProductoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Trabajador getJefeDeLinea() {
        return jefeDeLinea;
    }
    public void setJefeDeLinea(Trabajador jefeDeLinea) {
        this.jefeDeLinea = jefeDeLinea;
    }
}
