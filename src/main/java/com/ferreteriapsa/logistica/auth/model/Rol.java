package com.ferreteriapsa.logistica.auth.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {
    //atributos
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    //constructores
    public Rol() {
    }

    //getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
