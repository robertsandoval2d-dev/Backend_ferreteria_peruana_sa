package com.ferreteriapsa.logistica.trabajador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.trabajador.model.TrabajadorTienda;

public interface TrabajadorTiendaRepository extends JpaRepository<TrabajadorTienda, Long> {
    
}
