package com.ferreteriapsa.logistica.trabajador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.trabajador.model.TrabajadorLinea;

public interface TrabajadorLineaRepository extends JpaRepository<TrabajadorLinea, Long> {
    
}
