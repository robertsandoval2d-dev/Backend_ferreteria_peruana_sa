package com.ferreteriapsa.logistica.planificacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ferreteriapsa.logistica.planificacion.model.DetalleCronograma;

public interface DetalleCronogramaRepository extends JpaRepository<DetalleCronograma, Long> {

    List<DetalleCronograma> findAllByEstado(String estado);

    @Modifying
    @Query("""
        UPDATE DetalleCronograma dc 
        SET dc.estado = 'PROGRAMADO' 
        WHERE dc.productoProveedor.producto.productoId = :productoId 
        AND dc.productoProveedor.proveedor.proveedorId = :proveedorId 
        AND dc.estado = 'PENDIENTE'
    """)
    void actualizarEstadoAProgramado(@Param("productoId") Long productoId, @Param("proveedorId") Long proveedorId);
}