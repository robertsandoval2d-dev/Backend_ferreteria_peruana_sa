package com.ferreteriapsa.logistica.trabajador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.trabajador.model.TrabajadorAlmacen;

public interface TrabajadorAlmacenRepository extends JpaRepository<TrabajadorAlmacen, Long> {
    
}
