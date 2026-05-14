package com.ferreteriapsa.logistica.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ferreteriapsa.logistica.catalogo.model.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query(value = """
        SELECT 
            p.producto_id as productoId,
            p.nombre as productoNombre,
            pr.proveedor_id as proveedorId,
            pr.nombre as proveedorNombre
        FROM asignaciones a
        JOIN lineas_producto l ON a.linea_producto_id = l.linea_producto_id
        JOIN productos p ON p.linea_producto_id = l.linea_producto_id
        LEFT JOIN productos_proveedores pp ON pp.producto_id = p.producto_id
        LEFT JOIN proveedores pr ON pr.proveedor_id = pp.proveedor_id
        WHERE a.trabajador_id = :trabajadorId
        AND a.activo = true
        ORDER BY p.producto_id, pr.proveedor_id
    """, nativeQuery = true)
    List<CatalogoProjection> obtenerCatalogoPorTrabajador(
        @Param("trabajadorId") Long trabajadorId
    );
}
