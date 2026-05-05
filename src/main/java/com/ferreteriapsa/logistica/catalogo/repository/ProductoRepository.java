package com.ferreteriapsa.logistica.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ferreteriapsa.logistica.catalogo.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
