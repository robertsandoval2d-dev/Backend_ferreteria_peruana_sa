package com.ferreteriapsa.logistica.trabajador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ferreteriapsa.logistica.trabajador.model.Trabajador;
import com.ferreteriapsa.logistica.trabajador.dto.response.TrabajadorResponse;

import java.util.List;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {

    @Query("""
        SELECT new com.ferreteriapsa.logistica.trabajador.dto.response.TrabajadorResponse(
            t.trabajadorId, 
            r.nombre, 
            u.username, 
            u.activo, 
            t.nombre, 
            t.dni,
            t.mail,
            ti.tiendaId,
            ti.nombre,
            lp.lineaProductoId,
            lp.nombre
        )
        FROM Trabajador t
        JOIN t.usuario u
        JOIN u.rol r
        LEFT JOIN t.asignaciones a ON a.activo = true
        LEFT JOIN a.tienda ti
        LEFT JOIN a.lineaProducto lp
        ORDER BY r.nombre
    """)
    List<TrabajadorResponse> listarTrabajadoresConTienda();

}
