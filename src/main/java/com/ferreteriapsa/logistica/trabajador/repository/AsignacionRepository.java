package com.ferreteriapsa.logistica.trabajador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ferreteriapsa.logistica.trabajador.model.Asignacion;
import com.ferreteriapsa.logistica.trabajador.model.LineaProducto;
import com.ferreteriapsa.logistica.trabajador.model.Tienda;
import com.ferreteriapsa.logistica.trabajador.model.Trabajador;

public interface AsignacionRepository extends JpaRepository<Asignacion,Long>{
    Optional<Asignacion> findByTrabajadorAndActivoTrue(Trabajador trabajador); 
    Optional<Asignacion> findByTiendaAndLineaProductoAndActivoTrue(Tienda tienda, LineaProducto linea);
}
