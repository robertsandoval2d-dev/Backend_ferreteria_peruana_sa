package com.ferreteriapsa.logistica.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ferreteriapsa.logistica.inventario.model.ZonaAlmacen;

import java.util.List;

public interface ZonaAlmacenRepository extends JpaRepository<ZonaAlmacen,Long>{
    
    @Query("SELECT z FROM ZonaAlmacen z " +
            "JOIN z.almacen a " +
            "JOIN Tienda t ON t.almacen = a " +
            "JOIN Asignacion asig ON asig.tienda = t " +
            "WHERE asig.trabajador.trabajadorId = :trabajadorId " +
            "AND asig.activo = true")
        List<ZonaAlmacen> findZonasByTrabajadorId(@Param("trabajadorId") Long trabajadorId);
}
