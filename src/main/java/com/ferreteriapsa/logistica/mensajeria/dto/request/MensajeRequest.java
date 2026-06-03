package com.ferreteriapsa.logistica.mensajeria.dto.request;

public class MensajeRequest {
    private String titulo;
    private String mensaje;
    private String usernameDestino;

    public MensajeRequest() {}

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getUsernameDestino() { return usernameDestino; }
    public void setUsernameDestino(String usernameDestino) { this.usernameDestino = usernameDestino; }
}
