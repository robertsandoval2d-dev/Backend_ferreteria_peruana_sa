package com.ferreteriapsa.logistica.auth.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rol_id")
    private Long rolId;

    @Column(nullable = false, unique = true)
    private String nombre;

    //constructores
    public Rol() {
    }

    //getters y setters
    public Long getId() {
        return rolId;
    }
    public void setId(Long rolId) {
        this.rolId = rolId;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
