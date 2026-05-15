package com.ferreteriapsa.logistica.planificacion.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import com.ferreteriapsa.logistica.trabajador.model.Tienda;
import com.ferreteriapsa.logistica.trabajador.model.Trabajador;

@Entity
@Table(name = "cronogramas")
public class Cronograma {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cronograma_id")
    private Long cronogramaId;

    @Column(name="fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "trabajador_id")
    private Trabajador trabajador;

    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;

    @OneToMany(mappedBy = "cronograma")
    private List<DetalleCronograma> detallesCronograma;

    // constructores
    public Cronograma(){}

    //getters and setters
    public Long getCronogramaId() {
        return cronogramaId;
    }

    public void setCronogramaId(Long cronogramaId) {
        this.cronogramaId = cronogramaId;
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

    public Tienda getTienda(){
        return tienda;
    } 

    public void setTienda(Tienda tienda){
        this.tienda = tienda;
    }

    public List<DetalleCronograma> getDetallesCronograma(){
        return detallesCronograma;
    }

    public void setDetallesCronograma(List<DetalleCronograma> detallesCronograma){
        this.detallesCronograma = detallesCronograma;
    }
}
