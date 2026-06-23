package com.ferreteriapsa.logistica.inventario.dto.request;

import com.ferreteriapsa.logistica.inventario.model.Rotacion;

public class ProductoRotacionDTO {
    private Rotacion rotacion;

    public ProductoRotacionDTO() {
    }

    public Rotacion getRotacion() {
        return rotacion;
    }

    public void setRotacion(Rotacion rotacion) {
        this.rotacion = rotacion;
    }
}

