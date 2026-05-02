package com.ferreteriapsa.logistica.auth.model;

import com.ferreteriapsa.logistica.trabajador.model.Trabajador;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios") 
public class Usuario {
    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usuario_id")
    private Long usuarioId;
  
    @Column(nullable = false, unique = true)
    private String username;
   
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @Column(nullable = false)
    private boolean activo=true;

    @OneToOne(mappedBy = "usuario")
    private Trabajador trabajador;

    // constructores
    public Usuario(){
        
    }

    // getters y setters
    public Long getId() {
        return usuarioId;
    }
    public void setId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public Trabajador getTrabajador() {
        return trabajador;
    }

}