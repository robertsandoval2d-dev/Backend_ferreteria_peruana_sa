package com.ferreteriapsa.logistica.trabajador.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tiendas")
public class Tienda {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tienda_id;

    @Column(nullable = false)
    private String nombre;

    private String direccion;

    private String ciudad;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="almacen_id")
    private Almacen almacen;

    @OneToMany(mappedBy = "tienda")
    private List<Trabajador> trabajadores;

    @OneToMany(mappedBy = "tienda")
    private List<LineaProducto> lineaProductos;

    // constructores
    public Tienda() {}

    public Tienda(String nombre, String direccion, String ciudad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    // getters y setters

    public Long getId() {
        return tienda_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public Almacen getAlmacen() {
        return almacen;
    }
    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }
    public List<Trabajador> getTrabajadores() {
        return trabajadores;
    }
    public void setTrabajador(List<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    }
    public List<LineaProducto> getLineaProductos() {
        return lineaProductos;
    }
    public void setLineaProductos(List<LineaProducto> lineaProductos) {
        this.lineaProductos = lineaProductos;
    }
}