package com.ferreteriapsa.logistica.inventario.service;


import com.ferreteriapsa.logistica.compra.repository.OrdenCompraRepository;
import com.ferreteriapsa.logistica.compra.model.DetalleOrdenCompra;
import com.ferreteriapsa.logistica.compra.model.OrdenCompra;
import com.ferreteriapsa.logistica.compra.repository.DetalleOrdenCompraRepository;
import com.ferreteriapsa.logistica.inventario.dto.response.InventarioDTO;
import com.ferreteriapsa.logistica.inventario.dto.request.*;
import com.ferreteriapsa.logistica.inventario.model.Inventario;
import com.ferreteriapsa.logistica.inventario.model.ZonaAlmacen;
import com.ferreteriapsa.logistica.inventario.repository.*;

import com.ferreteriapsa.logistica.catalogo.repository.ProductoRepository;
import com.ferreteriapsa.logistica.catalogo.model.Producto;
import com.ferreteriapsa.logistica.trabajador.model.Almacen;
import com.ferreteriapsa.logistica.trabajador.model.Asignacion;
import com.ferreteriapsa.logistica.trabajador.model.Tienda;
import com.ferreteriapsa.logistica.trabajador.model.Trabajador;
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
    public void regitrarOrdenCompra(RegistroMercaderiaRequest request, Long trabajadorId){
        boolean entregaParcial = false;

        OrdenCompra ordenCompra = ordenCompraRepository.findById(request.getOrdenCompraId()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "La orden de compra no existe"
        ));

        if (ordenCompra.getEstado().equals("ENTREGADO") || ordenCompra.getEstado().equals("ENTREGADO-PARCIAL")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La orden ya fue recepcionada"
            );
        }

        Trabajador trabajador = trabajadorRepository.findById(trabajadorId)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Trabajador no encontrado"
                ));

        // Obtener la tienda activa del trabajador
        Tienda tienda = trabajador.getAsignaciones().stream()
            .filter(Asignacion::isActivo)
            .map(Asignacion::getTienda)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El trabajador no posee una tienda activa asignada"
            ));

        // Obtener el almacén vinculado a dicha tienda
        Almacen almacen = tienda.getAlmacen();
        if (almacen == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La tienda asociada no tiene un almacén configurado"
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

            int cantidadRecibida = productoRequest.getCantidad();

            if (cantidadRecibida < detalleOrdenCompra.getCantidad()) {
                entregaParcial = true;
            } else if (cantidadRecibida > detalleOrdenCompra.getCantidad()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "La cantidad recibida excede la cantidad solicitada");
            }

            Inventario inventario = inventarioRepository
                    .findByProductoProductoIdAndZonaAlmacenAlmacenAlmacenId(producto.getProductoId(), almacen.getAlmacenId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "El producto '" + producto.getNombre() + "' no se encuentra registrado en el inventario de este almacén"
                    ));

            ZonaAlmacen zonaAlmacen = inventario.getZonaAlmacen();

            inventario.setStock(inventario.getStock() + cantidadRecibida);
            zonaAlmacen.setCapacidadActual(zonaAlmacen.getCapacidadActual() + cantidadRecibida);
        }

        ordenCompra.setFechaEntrega(LocalDateTime.now());

        if (entregaParcial) {
            ordenCompra.setEstado("ENTREGADO-PARCIAL");
        } else if(ordenCompra.getFechaEntrega().isAfter(ordenCompra.getPlazoFechaMaximo())){
            ordenCompra.setEstado("ENTREGADO CON RETRASO");
        } else {
            ordenCompra.setEstado("ENTREGADO");
        }

        ordenCompraRepository.save(ordenCompra);
    }


    @Transactional
    public ProductoRotacionDTO cambiarRotacion(Long productoId, ProductoRotacionDTO request) {
        if(request.getRotacion() == null){
            throw new RuntimeException("La rotación es obligatoria");
        }

        Inventario inventario = inventarioRepository.findByProductoProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        inventario.setRotacion(request.getRotacion());

        inventarioRepository.save(inventario);

        ProductoRotacionDTO response = new ProductoRotacionDTO();
        response.setRotacion(inventario.getRotacion());

        return response;
    }
}
