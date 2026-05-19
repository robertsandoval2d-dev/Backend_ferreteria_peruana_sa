package com.ferreteriapsa.logistica.planificacion.service;

import com.ferreteriapsa.logistica.planificacion.repository.*;
import com.ferreteriapsa.logistica.planificacion.dto.request.*;
import com.ferreteriapsa.logistica.planificacion.dto.response.*;
import com.ferreteriapsa.logistica.planificacion.model.*;
import com.ferreteriapsa.logistica.catalogo.model.ProductoProveedor;
import com.ferreteriapsa.logistica.catalogo.repository.ProductoProveedorRepository;
import com.ferreteriapsa.logistica.trabajador.model.Asignacion;
import com.ferreteriapsa.logistica.trabajador.model.Tienda;
import com.ferreteriapsa.logistica.trabajador.model.Trabajador;
import com.ferreteriapsa.logistica.trabajador.repository.TrabajadorRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanificacionService {
  
    private final CronogramaRepository cronogramaRepository;
    private final DetalleCronogramaRepository detalleCronogramaRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final ProductoProveedorRepository productoProveedorRepository;

    public PlanificacionService(CronogramaRepository cronogramaRepository,DetalleCronogramaRepository detalleCronogramaRepository, 
        TrabajadorRepository trabajadorRepository, ProductoProveedorRepository productoProveedorRepository){
            this.cronogramaRepository = cronogramaRepository;
            this.detalleCronogramaRepository = detalleCronogramaRepository;
            this.trabajadorRepository = trabajadorRepository;
            this.productoProveedorRepository = productoProveedorRepository;
    }

    @Transactional
    public CronogramaResponse generarCronograma(Long trabajadorId, List<DetalleCronogramaRequest> detallesCronograma){

        // Crear cronograma
        Cronograma cronograma = new Cronograma();
        cronograma.setFechaCreacion(LocalDateTime.now());

        // Referenciar el trabajador
        @SuppressWarnings("null")
        Trabajador trabajador = trabajadorRepository.findById(trabajadorId)
            .orElseThrow(() -> new ResponseStatusException( //404 NOT FOUND
                    HttpStatus.NOT_FOUND,
                    "Trabajador no encontrado"
                ));
        cronograma.setTrabajador(trabajador);

        //referenciar la tienda
        Tienda tienda = trabajador.getAsignaciones().stream()
        .filter(Asignacion::isActivo)
        .map(Asignacion::getTienda)
        .findFirst()
        .orElseThrow(null);
        cronograma.setTienda(tienda);
        
        // Guardar cronograma primero
        cronograma = cronogramaRepository.save(cronograma);

        // Crear detalles
        List<DetalleCronograma> lista = new ArrayList<>();
        for (DetalleCronogramaRequest req : detallesCronograma) {

            DetalleCronograma detalle = new DetalleCronograma();

            detalle.setCantidad(req.getCantidad());
            detalle.setFechaRequerida(req.getFechaRequerida());
            detalle.setEstado("PENDIENTE");

            // referenciar productoProveedor
            ProductoProveedor pp = productoProveedorRepository
            .findByProducto_ProductoIdAndProveedor_ProveedorId(
                req.getProductoId(),
                req.getProveedorId()
            )
            .orElseThrow(() -> new ResponseStatusException( //400 BAD REQUEST
                    HttpStatus.BAD_REQUEST,
                    "Relación no válida"
                ));

            detalle.setProductoProveedor(pp);
            detalle.setCronograma(cronograma);

            lista.add(detalle);
        }
        detalleCronogramaRepository.saveAll(lista);

        // Response
        CronogramaResponse response = new CronogramaResponse();
        response.setCronogramaId(cronograma.getCronogramaId());
        response.setFechaCreacion(cronograma.getFechaCreacion());

        return response;
    }

    public List<ListaCronogramasResponse> listarCronogramasPendientesPorTrabajador(Long trabajadorId){

        List<Cronograma> cronogramas =
                cronogramaRepository
                        .listarCronogramasConDetallesPendientes(trabajadorId);

        return cronogramas.stream()
                .map(c -> new ListaCronogramasResponse(

                        c.getCronogramaId(),
                        c.getDetallesCronograma()
                                .get(0)
                                .getProductoProveedor()
                                .getProducto()
                                .getLineaProducto()
                                .getNombre(),

                        c.getDetallesCronograma()
                                .stream()
                                .map(dc -> new DetalleCronogramaDTO(

                                        dc.getProductoProveedor()
                                                .getProveedor()
                                                .getNombre(),

                                        dc.getProductoProveedor()
                                                .getProducto()
                                                .getNombre(),

                                        dc.getCantidad(),

                                        dc.getFechaRequerida()

                                ))
                                .toList()

                ))
                .toList();
    }

     public List<VistaPreviaResponse> obtenerVistaPreviaPendientes() {
// 1. Traemos solo los detalles que están realmente pendientes
    List<DetalleCronograma> detallesPendientes = detalleCronogramaRepository.findAllByEstado("PENDIENTE");

    // 2. Agrupamos por Proveedor (la lógica de agrupación sigue siendo eficiente)
    return detallesPendientes.stream()
        .collect(Collectors.groupingBy(
            detalle -> detalle.getProductoProveedor().getProveedor()
        ))
        .entrySet().stream()
        .map(entry -> {
            var proveedor = entry.getKey();
            var listaDetalles = entry.getValue();

            List<DetalleVistaPreviaDTO> detallesDTO = listaDetalles.stream()
                .map(d -> new DetalleVistaPreviaDTO(
                    d.getProductoProveedor().getProducto().getLineaProducto().getNombre(),
                    d.getProductoProveedor().getProducto().getProductoId(),
                    d.getProductoProveedor().getProducto().getNombre(),
                    d.getCantidad(),
                    d.getProductoProveedor().getPrecio(),
                    d.getFechaRequerida()
                ))
                .collect(Collectors.toList());

            BigDecimal montoTotal = detallesDTO.stream()
                .map(DetalleVistaPreviaDTO::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            return new VistaPreviaResponse(
                proveedor.getProveedorId(),
                proveedor.getNombre(),
                montoTotal,
                detallesDTO
            );
        })
        .collect(Collectors.toList());
    }
}
