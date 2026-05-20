package com.ferreteriapsa.logistica.compra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.compra.models.OrdenCompra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra,Long>{

    @Query("""
        SELECT DISTINCT oc
        FROM OrdenCompra oc
        JOIN FETCH oc.detalles d
        JOIN FETCH d.producto p
        WHERE oc.tienda.id = :idTienda
        AND oc.estado = 'PENDIENTE'
        AND oc.proveedor.id = :idProveedor
    """)
    List<OrdenCompra> listarOrdenesCompraPorTiendaYProveedor(
            @Param("idTienda") Long idTienda,
            @Param("idProveedor") Long idProveedor
    );

    @Query("""
        SELECT DISTINCT oc
        FROM OrdenCompra oc
        JOIN FETCH oc.detalles d
        JOIN FETCH d.producto p
        WHERE oc.tienda.id = :idTienda
        AND oc.estado = 'PENDIENTE'
    """)
    List<OrdenCompra> listarOrdenesCompraPorTienda(
            @Param("idTienda") Long idTienda
    );


}
