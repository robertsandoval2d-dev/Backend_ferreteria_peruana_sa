package com.ferreteriapsa.logistica.ventas.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ferreteriapsa.logistica.ventas.dto.response.PedidoRetrasadoResponse;
import com.ferreteriapsa.logistica.ventas.model.Pedido;
import com.ferreteriapsa.logistica.ventas.repository.PedidoRepository;
import com.ferreteriapsa.logistica.trabajador.model.Asignacion;
import com.ferreteriapsa.logistica.trabajador.model.Trabajador;
import com.ferreteriapsa.logistica.trabajador.repository.TrabajadorRepository;

@Service
public class VentasService {
    private final PedidoRepository pedidoRepository;
    private final TrabajadorRepository trabajadorRepository;

    public VentasService(PedidoRepository pedidoRepository, TrabajadorRepository trabajadorRepository){
        this.pedidoRepository = pedidoRepository;
        this.trabajadorRepository = trabajadorRepository;
    }

    @SuppressWarnings("null")
    public List<PedidoRetrasadoResponse> listarPedidosRetrasados(Long trabajadorId) {
        
        Trabajador trabajador = trabajadorRepository.findById(trabajadorId)
                .orElseThrow(() -> new ResponseStatusException(
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

        List<Pedido> pedidos = pedidoRepository.listarPedidosEntregadosTarde(tiendaId);

        List<PedidoRetrasadoResponse> response = pedidos.stream()
                .map(pedido -> {
                    PedidoRetrasadoResponse pr = new PedidoRetrasadoResponse();
                    
                    // Datos del cliente
                    pr.setClienteId(pedido.getCliente().getClienteId());
                    pr.setNombreCliente(pedido.getCliente().getNombre());
                    
                    // Datos del pedido
                    pr.setPedidoId(pedido.getPedidoId());
                    pr.setFechaMaximaEntrega(pedido.getFechaEntregaMaxima());
                    pr.setFechaEntrega(pedido.getFechaEntrega());
                    pr.setMontoTotalPedido(pedido.getMontoTotal());
                    Integer diasRetraso = (int) ChronoUnit.DAYS.between(
                        pedido.getFechaEntregaMaxima().toLocalDate(),
                        pedido.getFechaEntrega().toLocalDate()
                    );
                    pr.setDiasRetraso(diasRetraso);
                    pr.setEstado(pedido.getEstado());

                    return pr;
                }).toList();

        return response;
    }
    
}