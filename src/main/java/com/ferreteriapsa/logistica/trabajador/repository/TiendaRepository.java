package com.ferreteriapsa.logistica.trabajador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.trabajador.model.Tienda;

public interface TiendaRepository extends JpaRepository<Tienda, Long> {
    
}
