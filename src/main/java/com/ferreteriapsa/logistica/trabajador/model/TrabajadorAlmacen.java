package com.ferreteriapsa.logistica.trabajador.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trabajador_almacen")
public class TrabajadorAlmacen {
    //atributos
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Trabajador trabajador;

    @ManyToOne
    private Almacen almacen;

    //constructores
    public TrabajadorAlmacen() {
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
    public Almacen getAlmacen() {
        return almacen;
    }
    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }
}
