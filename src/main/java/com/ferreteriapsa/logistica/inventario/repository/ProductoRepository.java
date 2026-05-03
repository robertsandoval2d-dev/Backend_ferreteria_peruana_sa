package com.ferreteriapsa.logistica.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.inventario.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
