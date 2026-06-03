package com.ferreteriapsa.logistica.mensajeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ferreteriapsa.logistica.mensajeria.model.Mensaje;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    @Query("""
    SELECT m
    FROM Mensaje m
    JOIN FETCH m.emisor
    JOIN FETCH m.receptor
    WHERE m.emisor.usuarioId = :usuarioId
       OR m.receptor.usuarioId = :usuarioId
    ORDER BY m.fechaEnvio DESC
    """)
    List<Mensaje> listarMensajesPorUsuario(Long usuarioId);
}
