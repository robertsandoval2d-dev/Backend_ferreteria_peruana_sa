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
            t.nombre,
            t.dni
        )
        FROM Trabajador t
        JOIN t.usuario u
        JOIN u.rol r
        ORDER BY r.nombre
    """)
    List<TrabajadorResponse> listarTrabajadores();

}
