package com.ferreteriapsa.logistica.trabajador.dto.response;

public class TrabajadorResponse {
    private String nombre;
    private String username;
    private String rol;

    public TrabajadorResponse() {
    }
    public TrabajadorResponse(String nombre, String username, String rol) {
        this.nombre = nombre;
        this.username = username;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
}
