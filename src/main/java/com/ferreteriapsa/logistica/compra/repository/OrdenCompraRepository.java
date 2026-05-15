package com.ferreteriapsa.logistica.compra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.compra.models.OrdenCompra;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra,Long>{
    
}
