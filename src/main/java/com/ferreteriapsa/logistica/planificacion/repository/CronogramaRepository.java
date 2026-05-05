package com.ferreteriapsa.logistica.planificacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ferreteriapsa.logistica.planificacion.model.Cronograma;

public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {

}
