package com.ferreteriapsa.logistica.compra.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class OrdenesCompraResponse {

    private Long ordenCompraId;
    private String nombreProveedor;
    private LocalDateTime fechaEntrega;
    private LocalDateTime plazoFechaMaximo;
    private List<ProductoDTO> productos;


    public OrdenesCompraResponse() {

    }

    public OrdenesCompraResponse(Long ordenCompraId, String nombreProveedor, LocalDateTime fechaEntrega, LocalDateTime plazoFechaMaximo, List<ProductoDTO> productos) {
        this.ordenCompraId = ordenCompraId;
        this.nombreProveedor = nombreProveedor;
        this.fechaEntrega = fechaEntrega;
        this.plazoFechaMaximo = plazoFechaMaximo;
        this.productos = productos;
    }

    public Long getOrdenCompraId() {
        return ordenCompraId;
    }

    public void setOrdenCompraId(Long ordenCompraId) {
        this.ordenCompraId = ordenCompraId;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDateTime getPlazoFechaMaximo() {
        return plazoFechaMaximo;
    }

    public void setPlazoFechaMaximo(LocalDateTime plazoFechaMaximo) {
        this.plazoFechaMaximo = plazoFechaMaximo;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }
}
