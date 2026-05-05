package com.ferreteriapsa.logistica.catalogo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "proveedores")
public class Proveedor {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proveedor_id")
    private Long proveedorId;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @Column(name="mail_contacto", nullable = true)
    private String mailContacto;

    @NotBlank
    @Column(nullable = false)
    private String telefono;

    // constructores

    public Proveedor(){}

    public Proveedor(String nombre, String mailContacto, String telefono){
        this.nombre = nombre;
        this.mailContacto = mailContacto;
        this.telefono = telefono;
    }

    // getters and setters
    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMailContacto() {
        return mailContacto;
    }

    public void setMailContacto(String mailContacto) {
        this.mailContacto = mailContacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
