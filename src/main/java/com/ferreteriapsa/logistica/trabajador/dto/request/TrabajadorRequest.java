package com.ferreteriapsa.logistica.trabajador.dto.request;

public class TrabajadorRequest {
    // datos trabajador
    private String nombre;
    private String dni;

    // datos usuario
    private String username;
    private String password;
    private String rol;

    // asignación
    private Long tiendaId;
    private Long lineaId;

    public TrabajadorRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public Long getTiendaId() {
        return tiendaId;
    }

    public Long getLineaId() {
        return lineaId;
    }

}
