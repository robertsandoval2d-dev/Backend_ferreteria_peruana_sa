package com.ferreteriapsa.logistica.trabajador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.trabajador.model.Almacen;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
    
}
