package com.ferreteriapsa.logistica.catalogo.dto.response;

import java.util.List;

public class CatalogoResponse {

    private Long productoId;
    private String nombre;
    private List<ProveedorDTO> proveedores;

    public CatalogoResponse(Long productoId, String nombre, List<ProveedorDTO> proveedores) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.proveedores = proveedores;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ProveedorDTO> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<ProveedorDTO> proveedores) {
        this.proveedores = proveedores;
    }
}
