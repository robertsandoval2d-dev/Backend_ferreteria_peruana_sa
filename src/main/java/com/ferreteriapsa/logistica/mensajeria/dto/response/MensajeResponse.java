package com.ferreteriapsa.logistica.mensajeria.dto.response;

import java.time.LocalDateTime;

public class MensajeResponse {
    private Long mensajeId;
    private String titulo;
    private String mensaje;
    private Long emisorId;
    private String emisorUsername;
    private Long receptorId;
    private String receptorUsername;
    private LocalDateTime fechaEnvio;
    private Boolean leido;

    public MensajeResponse() {}

    public Long getMensajeId() { return mensajeId; }
    public void setMensajeId(Long mensajeId) { this.mensajeId = mensajeId; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Long getEmisorId() { return emisorId; }
    public void setEmisorId(Long emisorId) { this.emisorId = emisorId; }

    public String getEmisorUsername() { return emisorUsername; }
    public void setEmisorUsername(String emisorUsername) { this.emisorUsername = emisorUsername; }

    public Long getReceptorId() { return receptorId; }
    public void setReceptorId(Long receptorId) { this.receptorId = receptorId; }

    public String getReceptorUsername() { return receptorUsername; }
    public void setReceptorUsername(String receptorUsername) { this.receptorUsername = receptorUsername; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public Boolean getLeido() { return leido; }
    public void setLeido(Boolean leido) { this.leido = leido; }
}
