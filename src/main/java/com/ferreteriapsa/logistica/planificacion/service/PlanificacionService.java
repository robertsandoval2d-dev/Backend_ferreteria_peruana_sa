package com.ferreteriapsa.logistica.planificacion.service;

import com.ferreteriapsa.logistica.planificacion.repository.*;
import com.ferreteriapsa.logistica.planificacion.dto.request.*;
import com.ferreteriapsa.logistica.planificacion.dto.response.*;
import com.ferreteriapsa.logistica.planificacion.model.*;
import com.ferreteriapsa.logistica.catalogo.model.Producto;
import com.ferreteriapsa.logistica.catalogo.model.Proveedor;
import com.ferreteriapsa.logistica.catalogo.repository.ProductoRepository;
import com.ferreteriapsa.logistica.catalogo.repository.ProveedorRepository;
import com.ferreteriapsa.logistica.trabajador.model.Trabajador;
import com.ferreteriapsa.logistica.trabajador.repository.TrabajadorRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanificacionService {
  
    private final CronogramaRepository cronogramaRepository;
    private final DetalleCronogramaRepository detalleCronogramaRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;

    public PlanificacionService(CronogramaRepository cronogramaRepository,DetalleCronogramaRepository detalleCronogramaRepository, 
        TrabajadorRepository trabajadorRepository, ProductoRepository productoRepository, ProveedorRepository proveedorRepository){
            this.cronogramaRepository = cronogramaRepository;
            this.detalleCronogramaRepository = detalleCronogramaRepository;
            this.trabajadorRepository = trabajadorRepository;
            this.productoRepository = productoRepository;
            this.proveedorRepository = proveedorRepository;
    }

    @Transactional
    public CronogramaResponse generarCronograma(Long trabajadorId, List<DetalleCronogramaRequest> detallesCronograma){

        // Crear cronograma
        Cronograma cronograma = new Cronograma();
        cronograma.setEstado("pendiente");
        cronograma.setFechaCreacion(LocalDateTime.now());

        // Referenciar el trabajador
        @SuppressWarnings("null")
        Trabajador trabajador = trabajadorRepository.getReferenceById(trabajadorId);
        cronograma.setTrabajador(trabajador);
        
        // Guardar cronograma primero
        cronograma = cronogramaRepository.save(cronograma);

        // Crear detalles
        List<DetalleCronograma> lista = new ArrayList<>();
        for (DetalleCronogramaRequest req : detallesCronograma) {

            DetalleCronograma detalle = new DetalleCronograma();

            detalle.setCantidad(req.getCantidad());
            detalle.setFechaRequerida(req.getFechaRequerida());

            // referenciar producto
            @SuppressWarnings("null")
            Producto producto = productoRepository.getReferenceById(req.getProductoId());
            detalle.setProducto(producto);

            // referenciar proveedor
            @SuppressWarnings("null")
            Proveedor proveedor = proveedorRepository.getReferenceById(req.getProveedorId());
            detalle.setProveedor(proveedor);

            detalle.setCronograma(cronograma);

            lista.add(detalle);
        }
        detalleCronogramaRepository.saveAll(lista);

        // Response
        CronogramaResponse response = new CronogramaResponse();
        response.setCronogramaId(cronograma.getCronogramaId());
        response.setEstado(cronograma.getEstado());
        response.setFechaCreacion(cronograma.getFechaCreacion());

        return response;
    }
}
