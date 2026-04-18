package com.ferreteriapsa.logistica.trabajador.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trabajador_tienda")
public class TrabajadorTienda {
    //atributos
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Trabajador trabajador;

    @ManyToOne
    private Tienda tienda;

    //constructores
    public TrabajadorTienda() {
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
    public Tienda getTienda() {
        return tienda;
    }
    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
}
