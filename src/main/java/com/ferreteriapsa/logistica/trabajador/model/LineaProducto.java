package com.ferreteriapsa.logistica.trabajador.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "lineas_producto")
public class LineaProducto {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="linea_producto_id")
    private Long lineaProductoId;

    @Column(nullable = false, unique = false)
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "lineaProducto")
    private List<Asignacion> asignaciones;

    // constructores
    public LineaProducto() {}

    public LineaProducto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // getters y setters

    public Long getLineaProductoId() {
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

    public List<Asignacion> getAsignaciones(){
        return asignaciones;
    }
    public void setAsignaciones(List<Asignacion> asignaciones){
        this.asignaciones = asignaciones;
    }
}
