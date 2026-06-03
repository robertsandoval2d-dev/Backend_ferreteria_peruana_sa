package com.ferreteriapsa.logistica.mensajeria.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.ferreteriapsa.logistica.auth.model.Usuario;

@Entity
@Table(name = "mensajes")
public class Mensaje {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mensaje_id")
    private Long mensajeId;

    @Column(nullable = false)
    private String titulo;

    private String mensaje;

    @Column(nullable = false)
    private Boolean leido;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;

    @ManyToOne
    @JoinColumn(name = "emisor_id", nullable = false)
    private Usuario emisor;

    @ManyToOne
    @JoinColumn(name = "receptor_id", nullable = false)
    private Usuario receptor;

    public Mensaje(){}

    public Long getMensajeId() { return mensajeId; }
    public void setMensajeId(Long mensajeId) { this.mensajeId = mensajeId; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    
    public Boolean getLeido() { return leido; }
    public void setLeido(Boolean leido) { this.leido = leido; }
    
    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
    
    public Usuario getEmisor() { return emisor; }
    public void setEmisor(Usuario emisor) { this.emisor = emisor; }
    
    public Usuario getReceptor() { return receptor; }
    public void setReceptor(Usuario receptor) { this.receptor = receptor; }

}
