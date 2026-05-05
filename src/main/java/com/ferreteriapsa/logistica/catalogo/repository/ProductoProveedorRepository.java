package com.ferreteriapsa.logistica.catalogo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ferreteriapsa.logistica.catalogo.model.ProductoProveedor;

public interface ProductoProveedorRepository extends JpaRepository<ProductoProveedor, Long> {
    Optional<ProductoProveedor> findByProducto_ProductoIdAndProveedor_ProveedorId(
            Long productoId,
            Long proveedorId
    );
}
