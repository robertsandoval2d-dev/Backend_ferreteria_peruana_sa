package com.ferreteriapsa.logistica.trabajador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.trabajador.model.LineaProducto;

public interface LineaProductoRepository extends JpaRepository<LineaProducto, Long> {
    
}
