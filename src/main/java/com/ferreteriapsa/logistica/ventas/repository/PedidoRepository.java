package com.ferreteriapsa.logistica.ventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ferreteriapsa.logistica.ventas.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    @Query("""
        SELECT DISTINCT p
        FROM Pedido p
        JOIN FETCH p.cliente c
        WHERE p.tienda.tiendaId = :idTienda
        AND p.fechaEntrega IS NOT NULL
        AND p.fechaEntrega > p.fechaEntregaMaxima
    """)
    List<Pedido> listarPedidosEntregadosTarde(@Param("idTienda") Long idTienda);
    
}
