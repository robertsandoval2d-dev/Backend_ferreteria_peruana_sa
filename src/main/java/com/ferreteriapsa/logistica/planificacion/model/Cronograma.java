package com.ferreteriapsa.logistica.planificacion.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import com.ferreteriapsa.logistica.trabajador.model.Trabajador;

@Entity
@Table(name = "cronogramas")
public class Cronograma {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cronograma_id")
    private Long cronogramaId;

    @Column(nullable = false)   
    private String estado;

    @Column(name="fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "trabajador_id")
    private Trabajador trabajador;

    @OneToMany(mappedBy = "cronograma")
    private List<DetalleCronograma> detallesCronograma;

    // constructores
    public Cronograma(){}

    public Cronograma(String estado, LocalDateTime fechaCreacion){
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    //getters and setters
    public Long getCronogramaId() {
        return cronogramaId;
    }

    public void setCronogramaId(Long cronogramaId) {
        this.cronogramaId = cronogramaId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
}
