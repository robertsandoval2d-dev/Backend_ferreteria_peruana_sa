package com.ferreteriapsa.logistica.trabajador.model;

import jakarta.persistence.*;
import com.ferreteriapsa.logistica.auth.model.Usuario;

@Entity
@Table(name = "trabajadores")
public class Trabajador {
    // atributos    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trabajador_id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String dni;

    // Relación con Usuario (1 a 1)
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tienda_id", nullable = true)
    private Tienda tienda;


    // constructores
    public Trabajador() {}

    public Trabajador(String nombre, String dni, Usuario usuario) {
        this.nombre = nombre;
        this.dni = dni;
        this.usuario = usuario;
    }

    // getters y setters

    public Long getId() {
        return trabajador_id;
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
    public Tienda getTienda() {
        return tienda;
    }
    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

}
