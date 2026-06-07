package com.ferreteriapsa.logistica.penalidad.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ferreteriapsa.logistica.penalidad.dto.request.*;
import com.ferreteriapsa.logistica.penalidad.dto.response.OrdenConRetrasoResponse;
//import com.ferreteriapsa.logistica.penalidad.dto.response.*;
import com.ferreteriapsa.logistica.penalidad.model.*;
import com.ferreteriapsa.logistica.compra.model.OrdenCompra;
import com.ferreteriapsa.logistica.penalidad.repository.*;
import com.ferreteriapsa.logistica.trabajador.model.Asignacion;
import com.ferreteriapsa.logistica.trabajador.model.Trabajador;
import com.ferreteriapsa.logistica.trabajador.repository.TrabajadorRepository;
import com.ferreteriapsa.logistica.compra.repository.OrdenCompraRepository;

import jakarta.transaction.Transactional;

@Service
public class PenalidadService {
    private final PenalidadRepository penalidadRepository;
    private final OrdenCompraRepository ordenCompraRepository;
    private final TrabajadorRepository trabajadorRepository;

    public PenalidadService(PenalidadRepository penalidadRepository, OrdenCompraRepository ordenCompraRepository, TrabajadorRepository trabajadorRepository) {
        this.penalidadRepository = penalidadRepository;
        this.ordenCompraRepository = ordenCompraRepository;
        this.trabajadorRepository = trabajadorRepository;
    }

    @SuppressWarnings("null")
    @Transactional
    public void registrarPenalidad(PenalidadRequest request){

        Penalidad penalidad = new Penalidad();

        OrdenCompra ordenCompra = ordenCompraRepository.getReferenceById(request.getOrdenCompraId());

        penalidad.setOrdenCompra(ordenCompra);
        penalidad.setDiasRetraso(request.getDiasRetraso());
        penalidad.setMontoPenalidad(request.getMontoPenalidad());
        penalidad.setEstadoPago("NO PAGADO");

        penalidadRepository.save(penalidad);
    }

    @SuppressWarnings("null")
    public List<OrdenConRetrasoResponse> listarOrdenesCompraConRetraso(Long trabajadorId){

        Trabajador trabajador = trabajadorRepository.findById(trabajadorId)
                .orElseThrow(() -> new ResponseStatusException( //404 NOT FOUND
                        HttpStatus.NOT_FOUND,
                        "Trabajador no encontrado"
                ));

        Long tiendaId = trabajador.getAsignaciones().stream()
                .filter(Asignacion::isActivo)
                .map(asignacion -> asignacion.getTienda().getTiendaId())
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "El trabajador no tiene tienda activa")
                );

        List<OrdenCompra> ordenes = ordenCompraRepository.listarOrdenesCompraConRetraso(tiendaId);

        List<OrdenConRetrasoResponse> response = ordenes.stream()
                .map(orden -> {
                    OrdenConRetrasoResponse or = new OrdenConRetrasoResponse();
                    or.setOrdenCompraId(orden.getOrdenCompraId());
                    or.setProveedor(orden.getProveedor().getNombre());
                    or.setFechaEntrega(orden.getFechaEntrega());
                    or.setFechaLimite(orden.getPlazoFechaMaximo());
                    or.setEstado(orden.getEstado());

                    return or;
                }).toList();

        return response;
    }

}
