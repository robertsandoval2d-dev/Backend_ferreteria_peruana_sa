package com.ferreteriapsa.logistica.inventario.repository;

import com.ferreteriapsa.logistica.inventario.dto.response.InventarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.inventario.model.Inventario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    @Query("SELECT new com.ferreteriapsa.logistica.inventario.dto.response.InventarioDTO(" +
            "p.productoId, p.nombre, i.stock, i.stockMin, CAST(i.rotacion AS string), p.categoria) " +
            "FROM Inventario i " +
            "JOIN i.producto p " +
            "WHERE p.lineaProducto.jefeDeLinea.trabajadorId = :trabajadorId")
    List<InventarioDTO> buscarProductosPorJefeId(@Param("trabajadorId") Long trabajadorId);
}
