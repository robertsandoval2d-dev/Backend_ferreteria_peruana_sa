package com.ferreteriapsa.logistica.inventario.dto.request;

import java.util.List;

public class RegistroMercaderiaRequest {
    private Long ordenCompraId;
    private List<RecepcionProductoDTO> productos;

    public RegistroMercaderiaRequest(Long ordenCompraId, List<RecepcionProductoDTO> productos) {
        this.ordenCompraId = ordenCompraId;
        this.productos = productos;
    }

    public Long getOrdenCompraId() {
        return ordenCompraId;
    }

    public void setOrdenCompraId(Long ordenCompraId) {
        this.ordenCompraId = ordenCompraId;
    }

    public List<RecepcionProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<RecepcionProductoDTO> productos) {
        this.productos = productos;
    }
}
