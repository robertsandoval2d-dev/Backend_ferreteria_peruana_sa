package com.ferreteriapsa.logistica.penalidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ferreteriapsa.logistica.penalidad.model.Penalidad;

public interface PenalidadRepository extends JpaRepository<Penalidad,Long>{

    
}
