package com.ferreteriapsa.logistica.trabajador.dto.response;

public class TrabajadorUpdateResponse {

    private Long trabajadorId;
    private String nombre;
    private String dni;
    private String nombreTienda;
    private String nombreLinea;

    public TrabajadorUpdateResponse() {
    }

    public Long getTrabajadorId() {
        return trabajadorId;
    }
    public void setTrabajadorId(Long trabajadorId) {
        this.trabajadorId = trabajadorId;
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
    public String getNombreTienda() {
        return nombreTienda;
    }
    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }
    public String getNombreLinea() {
        return nombreLinea;
    }
    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }
}