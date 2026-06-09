package com.ferreteriapsa.logistica.trabajador.model;

import jakarta.persistence.*;
import com.ferreteriapsa.logistica.auth.model.Usuario;
import java.util.List;

@Entity
@Table(name = "trabajadores")
public class Trabajador {
    // atributos    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trabajador_id")
    private Long trabajadorId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false, unique = true)
    private String mail;
    // Relación con Usuario (1 a 1)
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "trabajador")
    private List<Asignacion> asignaciones;


    // constructores
    public Trabajador() {}

    public Trabajador(String nombre, String dni, Usuario usuario) {
        this.nombre = nombre;
        this.dni = dni;
        this.usuario = usuario;
    }

    // getters y setters

    public Long getTrabajadorId() {
        return trabajadorId;
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

    public String getMail(){
        return mail;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Asignacion> getAsignaciones(){
        return asignaciones;
    }
    public void setAsignaciones(List<Asignacion> asignaciones){
        this.asignaciones = asignaciones;
    }
}
