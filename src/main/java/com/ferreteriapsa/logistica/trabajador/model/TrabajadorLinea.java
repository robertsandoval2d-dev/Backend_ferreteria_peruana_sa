package com.ferreteriapsa.logistica.trabajador.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trabajador_linea")
public class TrabajadorLinea {
    //atributos
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Trabajador trabajador;

    @ManyToOne
    private LineaProducto linea;

    //constructores
    public TrabajadorLinea() {
    }

    //getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }   
    public Trabajador getTrabajador() {
        return trabajador;
    }
    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
    public LineaProducto getLinea() {
        return linea;
    }
    public void setLinea(LineaProducto linea) {
        this.linea = linea;
    }
}