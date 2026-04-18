package com.ferreteriapsa.logistica.trabajador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.trabajador.model.Trabajador;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {
    
}
