package com.ferreteriapsa.logistica.ventas.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ferreteriapsa.logistica.ventas.dto.response.PedidoRetrasado;
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
    public List<PedidoRetrasado> listarPedidosRetrasados(Long trabajadorId) {
        
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

        List<PedidoRetrasado> response = pedidos.stream()
                .map(pedido -> {
                    PedidoRetrasado pr = new PedidoRetrasado();
                    
                    // Datos del cliente
                    pr.setClienteId(pedido.getCliente().getClienteId());
                    pr.setNombreCliente(pedido.getCliente().getNombre());
                    
                    // Datos del pedido
                    pr.setPedidoId(pedido.getPedidoId());
                    pr.setFechaMaximaEntrega(pedido.getFechaEntregaMaxima());
                    pr.setFechaEntrega(pedido.getFechaEntrega());
                    pr.setMontoTotalPedido(pedido.getMontoTotal());

                    return pr;
                }).toList();

        return response;
    }
    
}