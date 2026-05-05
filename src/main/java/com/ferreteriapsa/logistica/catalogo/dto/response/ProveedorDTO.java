package com.ferreteriapsa.logistica.catalogo.dto.response;

public class ProveedorDTO {

    private Long proveedorId;
    private String nombre;

    public ProveedorDTO(){}

    public ProveedorDTO(Long proveedorId, String nombre){
        this.proveedorId = proveedorId;
        this.nombre = nombre;
    }
    // Getters y Setters
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
}
