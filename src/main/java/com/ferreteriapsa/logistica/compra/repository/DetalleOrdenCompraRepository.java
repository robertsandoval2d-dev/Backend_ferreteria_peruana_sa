package com.ferreteriapsa.logistica.compra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.compra.models.DetalleOrdenCompra;

import java.util.Optional;

public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompra,Long>{
    Optional<DetalleOrdenCompra> findByOrdenCompraOrdenCompraIdAndProductoProductoId(
            Long ordenCompraId,
            Long productoId
    );
    
}
