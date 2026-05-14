package com.ferreteriapsa.logistica.planificacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ferreteriapsa.logistica.planificacion.model.Cronograma;

import java.util.List;

public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {
    // @Query("""
    //     SELECT DISTINCT c
    //     FROM Cronograma c
    //     JOIN FETCH c.tienda t
    //     JOIN FETCH c.detallesCronograma dc
    //     JOIN FETCH dc.productoProveedor pp
    //     JOIN FETCH pp.producto p
    //     JOIN FETCH p.lineaProducto lp
    //     JOIN FETCH pp.proveedor pr
    //     WHERE t.tiendaId = (
    //         SELECT tr.tienda.tiendaId
    //         FROM Trabajador tr
    //         WHERE tr.trabajadorId = :trabajadorId
    //     )
    //     AND c.estado = 'pendiente'
    // """)
    // List<Cronograma> listarCronogramasPendientesPorTrabajador(
    //         @Param("trabajadorId") Long trabajadorId
    // );
}
