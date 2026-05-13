package com.ferreteriapsa.logistica.trabajador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ferreteriapsa.logistica.trabajador.model.Tienda;

import java.util.List;

public interface TiendaRepository extends JpaRepository<Tienda, Long> {
    @Query("""
        SELECT DISTINCT t
        FROM Tienda t
        LEFT JOIN FETCH t.lineaProductos
        ORDER BY t.nombre
    """)
    List<Tienda> listarTiendasConLineas();
}
