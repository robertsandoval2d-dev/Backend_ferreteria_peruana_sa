package com.ferreteriapsa.logistica.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ferreteriapsa.logistica.ventas.model.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido,Long>{
    
}
