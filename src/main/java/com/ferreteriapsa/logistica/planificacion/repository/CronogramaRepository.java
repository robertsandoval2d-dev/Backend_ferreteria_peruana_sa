package com.ferreteriapsa.logistica.planificacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ferreteriapsa.logistica.planificacion.model.Cronograma;

import java.util.List;

public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {
    @Query("""
        SELECT DISTINCT c
        FROM Cronograma c
        JOIN FETCH c.tienda t
        JOIN FETCH c.detallesCronograma dc
        JOIN FETCH dc.productoProveedor pp
        JOIN FETCH pp.producto p
        JOIN FETCH p.lineaProducto lp
        JOIN FETCH pp.proveedor pr
        WHERE t.tiendaId IN (
            SELECT a.tienda.tiendaId
            FROM Asignacion a
            WHERE a.trabajador.trabajadorId = :trabajadorId
            AND a.activo = true
        )
        AND dc.estado = 'PENDIENTE'
    """)
    List<Cronograma> listarCronogramasConDetallesPendientes(
        @Param("trabajadorId") Long trabajadorId
    );
}
