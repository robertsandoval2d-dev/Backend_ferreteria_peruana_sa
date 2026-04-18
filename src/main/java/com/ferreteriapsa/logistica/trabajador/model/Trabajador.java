package com.ferreteriapsa.logistica.trabajador.model;

import jakarta.persistence.*;
import com.ferreteriapsa.logistica.auth.model.Usuario;

@Entity
@Table(name = "trabajadores")
public class Trabajador {
    // atributos    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String dni;

    // Relación con Usuario (1 a 1)
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "almacen_id")
    private Almacen almacen;

    // constructores
    public Trabajador() {}

    public Trabajador(String nombre, String dni, Usuario usuario) {
        this.nombre = nombre;
        this.dni = dni;
        this.usuario = usuario;
    }

    // getters y setters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Almacen getAlmacen() {
        return almacen;
    }
    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

}
