package com.ferreteriapsa.logistica.compra.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import com.ferreteriapsa.logistica.compra.repository.OrdenCompraRepository;
import com.ferreteriapsa.logistica.catalogo.model.Producto;
import com.ferreteriapsa.logistica.catalogo.model.Proveedor;
import com.ferreteriapsa.logistica.catalogo.repository.ProductoRepository;
import com.ferreteriapsa.logistica.catalogo.repository.ProveedorRepository;
import com.ferreteriapsa.logistica.compra.dto.request.OrdenCompraRequest;
import com.ferreteriapsa.logistica.compra.dto.response.OrdenCompraResponse;
import com.ferreteriapsa.logistica.compra.models.DetalleOrdenCompra;
import com.ferreteriapsa.logistica.compra.models.OrdenCompra;
import com.ferreteriapsa.logistica.planificacion.repository.DetalleCronogramaRepository;
import com.ferreteriapsa.logistica.trabajador.model.Asignacion;
import com.ferreteriapsa.logistica.trabajador.model.Tienda;
import com.ferreteriapsa.logistica.trabajador.model.Trabajador;
import com.ferreteriapsa.logistica.trabajador.repository.TrabajadorRepository;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.http.HttpStatus;

@Service
public class CompraService {
    private final TrabajadorRepository trabajadorRepository;
    private final OrdenCompraRepository ordenCompraRepository;
    private final ProveedorRepository proveedorRepository;
    private final ProductoRepository productoRepository;
    private final DetalleCronogramaRepository detalleCronogramaRepository;

    public CompraService(OrdenCompraRepository ordenCompraRepository,
                            ProveedorRepository proveedorRepository,
                            ProductoRepository productoRepository,
                            DetalleCronogramaRepository detalleCronogramaRepository,
                            TrabajadorRepository trabajadorRepository) {
            this.ordenCompraRepository = ordenCompraRepository;
            this.proveedorRepository = proveedorRepository;
            this.productoRepository = productoRepository;
            this.detalleCronogramaRepository = detalleCronogramaRepository;
            this.trabajadorRepository = trabajadorRepository;
    }

    @SuppressWarnings("null")
    @Transactional
    public OrdenCompraResponse generarOrdenCompra(OrdenCompraRequest request, Long trabajadorId) {
        // Validamos el proveedor porque su ID viene del cuerpo del request
        Proveedor proveedor = proveedorRepository.findById(request.getProveedorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado"));

        // Referenciar el trabajador
        Trabajador trabajador = trabajadorRepository.findById(trabajadorId)
            .orElseThrow(() -> new ResponseStatusException( //404 NOT FOUND
                    HttpStatus.NOT_FOUND,
                    "Trabajador no encontrado"
                ));

        //referenciar la tienda
        Tienda tienda = trabajador.getAsignaciones().stream()
        .filter(Asignacion::isActivo)
        .map(Asignacion::getTienda)
        .findFirst()
        .orElseThrow(null);

        OrdenCompra ordenCompra = new OrdenCompra();
        ordenCompra.setProveedor(proveedor);
        
        // Para el administrador usamos getReferenceById ya que el ID es confiable (viene del token)
        ordenCompra.setAdministrador(trabajador);
        ordenCompra.setTienda(tienda);
        
        ordenCompra.setPlazoFechaMaximo(request.getPlazoFechaMaximo());
        ordenCompra.setMontoTotalCalculado(request.getMontoTotalCalculado());
        ordenCompra.setEstado("PENDIENTE");
        ordenCompra.setFechaCreacion(LocalDateTime.now());

        List<DetalleOrdenCompra> detalles = request.getDetalles().stream().map(detalleDto -> {
            // Validamos que el producto exista
            Producto producto = productoRepository.findById(detalleDto.getProductoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

            DetalleOrdenCompra detalle = new DetalleOrdenCompra();
            detalle.setNombreLinea(producto.getLineaProducto().getNombre());
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setPrecioUnidad(detalleDto.getPrecioUnidad());
            detalle.setOrdenCompra(ordenCompra);

            // Actualizamos los detalles del cronograma a 'PROGRAMADO'
            detalleCronogramaRepository.actualizarEstadoAProgramado(
                    producto.getProductoId(), 
                    proveedor.getProveedorId(),
                    trabajadorId
            );

            return detalle;
        }).collect(Collectors.toList());

        ordenCompra.setDetalles(detalles);

        OrdenCompra guardada = ordenCompraRepository.save(ordenCompra);

        OrdenCompraResponse res = new OrdenCompraResponse();
        res.setOrdenCompraId(guardada.getOrdenCompraId());
        res.setEstado(guardada.getEstado());
        res.setFechaCreacion(guardada.getFechaCreacion());
        
        return res;
    }
    
}
