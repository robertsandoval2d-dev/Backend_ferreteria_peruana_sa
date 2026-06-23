package com.ferreteriapsa.logistica.inventario.repository;

import com.ferreteriapsa.logistica.analisis.dto.response.VentasStockResponse;
import com.ferreteriapsa.logistica.inventario.dto.response.InventarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.inventario.model.Inventario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    @Query("""
        SELECT new com.ferreteriapsa.logistica.inventario.dto.response.InventarioDTO(
            p.productoId, 
            p.nombre, 
            i.stock, 
            i.stockMin, 
            CAST(i.rotacion AS string), 
            p.categoria
        )
        FROM Inventario i
        JOIN i.producto p
        WHERE EXISTS (
            SELECT 1
            FROM Asignacion a
            WHERE a.trabajador.trabajadorId = :trabajadorId
            AND a.activo = true
            AND a.lineaProducto = p.lineaProducto
            AND a.tienda.almacen = i.zonaAlmacen.almacen
        )
    """)
    List<InventarioDTO> buscarProductosPorJefeId(@Param("trabajadorId") Long trabajadorId);

    Optional<Inventario> findByProductoProductoIdAndZonaAlmacenAlmacenAlmacenId(Long productoId, Long almacenId);

    @Query("SELECT i FROM Inventario i " +
           "JOIN FETCH i.producto p " +
           "JOIN i.zonaAlmacen z " +
           "JOIN z.almacen a " +
           "JOIN Tienda t ON t.almacen = a " +
           "JOIN Asignacion asig ON asig.tienda = t AND asig.lineaProducto = p.lineaProducto " +
           "WHERE asig.trabajador.trabajadorId = :trabajadorId " +
           "AND asig.activo = true")
    List<Inventario> findInventarioPorLineaYTiendaDelTrabajador(@Param("trabajadorId") Long trabajadorId);

    @Query("SELECT new com.ferreteriapsa.logistica.analisis.dto.response.VentasStockResponse(" +
           "p.nombre, COUNT(dp.detallePedidoId), i.stock) " +
           "FROM Inventario i " +
           "JOIN i.producto p " +
           "JOIN i.zonaAlmacen z " +
           "JOIN z.almacen a " +
           "JOIN Tienda t ON t.almacen = a " +
           "JOIN Asignacion asig ON asig.tienda = t AND asig.lineaProducto = p.lineaProducto " +
           // Añadimos la condición de fecha directamente en el ON del LEFT JOIN
           "LEFT JOIN DetallePedido dp ON dp.producto = p AND dp.pedido.tienda = t AND dp.pedido.fechaCreacion >= :fechaLimite " +
           "WHERE asig.trabajador.trabajadorId = :trabajadorId " +
           "AND asig.activo = true " +
           "GROUP BY p.nombre, i.stock")
    List<VentasStockResponse> findVentasYStockPorTrabajador(
            @Param("trabajadorId") Long trabajadorId, 
            @Param("fechaLimite") LocalDateTime fechaLimite
    );


    Optional<Inventario> findByProductoProductoId(Long productoId);

}
