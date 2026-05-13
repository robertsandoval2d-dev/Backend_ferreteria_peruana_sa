package com.ferreteriapsa.logistica.trabajador.dto.request;

public class TrabajadorUpdateRequest {
    private String nombre;
    private String dni;
    private Long tiendaId;
    private Long lineaId;

    public TrabajadorUpdateRequest(){}

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
    public Long getTiendaId() {
        return tiendaId;
    }
    public void setTiendaId(Long tiendaId) {
        this.tiendaId = tiendaId;
    }
    public Long getLineaId() {
        return lineaId;
    }
    public void setLineaId(Long lineaId) {
        this.lineaId = lineaId;
    }


}
