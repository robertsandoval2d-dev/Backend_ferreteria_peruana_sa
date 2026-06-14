package com.ferreteriapsa.logistica.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ferreteriapsa.logistica.ventas.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{

    
}
