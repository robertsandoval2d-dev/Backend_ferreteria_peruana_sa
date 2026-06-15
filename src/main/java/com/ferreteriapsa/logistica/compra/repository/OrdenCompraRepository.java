package com.ferreteriapsa.logistica.compra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ferreteriapsa.logistica.compra.model.OrdenCompra;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra,Long>{

    @Query("""
        SELECT DISTINCT oc
        FROM OrdenCompra oc
        JOIN FETCH oc.detalles d
        JOIN FETCH d.producto p
        WHERE oc.tienda.tiendaId = :idTienda
        AND oc.ordenCompraId = :idOrden
    """)
    List<OrdenCompra> listarOrdenesCompraPorTiendaYOrdenCompra(
            @Param("idTienda") Long idTienda,
            @Param("idOrden") Long idOrden
    );

    @Query("""
        SELECT DISTINCT oc
        FROM OrdenCompra oc
        JOIN FETCH oc.detalles d
        JOIN FETCH d.producto p
        WHERE oc.tienda.tiendaId = :idTienda
        AND oc.estado = 'PENDIENTE'
    """)
    List<OrdenCompra> listarOrdenesCompraPorTienda(
            @Param("idTienda") Long idTienda
    );

    @Query("""
        SELECT oc
        FROM OrdenCompra oc
        WHERE oc.tienda.tiendaId = :idTienda    
        AND oc.fechaCreacion >= :fechaDesde   
    """)
    List<OrdenCompra> listarOrdenesCompraPorTiendaHastaMesPasado(
            @Param("idTienda") Long idTienda,
            @Param("fechaDesde") LocalDateTime fechaDesde 
    );

//    @Query("""
//        SELECT DISTINCT oc
//        FROM OrdenCompra oc
//        JOIN FETCH oc.proveedor p
//        WHERE oc.tienda.tiendaId = :idTienda
//        AND oc.estado = 'ENTREGADO CON RETRASO'
//    """)
//    List<OrdenCompra> listarOrdenesCompraConRetraso(
//            @Param("idTienda") Long idTienda
//    );

    @Query("""
        SELECT o 
        FROM OrdenCompra o 
        WHERE o.tienda.tiendaId = :tiendaId
        AND o.estado = 'ENTREGADO CON RETRASO'
        AND NOT EXISTS (SELECT p FROM Penalidad p WHERE p.ordenCompra = o)
    """)
    List<OrdenCompra> listarOrdenesCompraConRetraso(
            @Param("tiendaId") Long tiendaId
    );

}
