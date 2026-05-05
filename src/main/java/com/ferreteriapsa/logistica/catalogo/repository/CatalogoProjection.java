package com.ferreteriapsa.logistica.catalogo.repository;

public interface CatalogoProjection {

    Long getProductoId();
    String getProductoNombre();

    Long getProveedorId();
    String getProveedorNombre();
}
