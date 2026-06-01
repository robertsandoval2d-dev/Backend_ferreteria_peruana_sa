package com.ferreteriapsa.logistica.inventario.service;


import com.ferreteriapsa.logistica.compra.models.DetalleOrdenCompra;
import com.ferreteriapsa.logistica.compra.repository.OrdenCompraRepository;
import com.ferreteriapsa.logistica.compra.repository.DetalleOrdenCompraRepository;
import com.ferreteriapsa.logistica.compra.models.OrdenCompra;

import com.ferreteriapsa.logistica.inventario.dto.response.InventarioDTO;
import com.ferreteriapsa.logistica.inventario.dto.request.*;
import com.ferreteriapsa.logistica.inventario.model.Inventario;
import com.ferreteriapsa.logistica.inventario.repository.*;

import com.ferreteriapsa.logistica.catalogo.repository.ProductoRepository;
import com.ferreteriapsa.logistica.catalogo.model.Producto;

import com.ferreteriapsa.logistica.trabajador.repository.TrabajadorRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final OrdenCompraRepository ordenCompraRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final ProductoRepository productoRepository;
    private final DetalleOrdenCompraRepository detalleOrdenCompraRepository;

    public InventarioService(InventarioRepository inventarioRepository, OrdenCompraRepository ordenCompraRepository,
                             TrabajadorRepository trabajadorRepository, ProductoRepository productoRepository,
                             DetalleOrdenCompraRepository detalleOrdenCompraRepository) {
        this.inventarioRepository = inventarioRepository;
        this.ordenCompraRepository = ordenCompraRepository;
        this.trabajadorRepository = trabajadorRepository;
        this.productoRepository = productoRepository;
        this.detalleOrdenCompraRepository = detalleOrdenCompraRepository;
    }

    @Transactional(readOnly = true)
    public List<InventarioDTO> listarInventarioLinea(Long trabajadorId){

        return inventarioRepository.buscarProductosPorJefeId(trabajadorId);
    }

    //ALMACENERO-POST
    @SuppressWarnings("null")
    @Transactional
    public void regitrarOrdenCompra(RegistroMercaderiaRequest request){
        boolean entregaParcial = false;

        OrdenCompra ordenCompra = ordenCompraRepository.findById(request.getOrdenCompraId()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "La orden de compra no existe"
        ));

        if (
                ordenCompra.getEstado().equals("ENTREGADO") ||
                        ordenCompra.getEstado().equals("ENTREGADO-PARCIAL")
        ) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La orden ya fue recepcionada"
            );
        }

        for(RecepcionProductoDTO productoRequest: request.getProductos()){
            Producto producto = productoRepository.findById(productoRequest.getProductoId()).orElseThrow(() -> new RuntimeException(
                    "El producto no existe"
            ));

            DetalleOrdenCompra detalleOrdenCompra = detalleOrdenCompraRepository.findByOrdenCompraOrdenCompraIdAndProductoProductoId(
                            ordenCompra.getOrdenCompraId(),
                            producto.getProductoId()
                    )
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "El producto no pertenece a la orden"
                    ));

            if (productoRequest.getCantidad() < detalleOrdenCompra.getCantidad()) {
                entregaParcial = true;
            } else if (productoRequest.getCantidad() > detalleOrdenCompra.getCantidad()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "La cantidad recibida excede la cantidad solicitada");
            }



            Inventario inventario = inventarioRepository
                    .findByProductoProductoId(producto.getProductoId())
                    .orElseThrow(() -> new RuntimeException(
                            "El producto no existe en inventario"
                    ));
            inventario.setStock(inventario.getStock() + productoRequest.getCantidad());

        }

        ordenCompra.setFechaEntrega(LocalDateTime.now());

        if (entregaParcial) {
            ordenCompra.setEstado("ENTREGADO-PARCIAL");
        }else if(ordenCompra.getFechaEntrega().isAfter(ordenCompra.getPlazoFechaMaximo())){
            ordenCompra.setEstado("ENTREGADO CON RETRASO");
        }
        else {
            ordenCompra.setEstado("ENTREGADO");
        }

        ordenCompraRepository.save(ordenCompra);

    }

}
