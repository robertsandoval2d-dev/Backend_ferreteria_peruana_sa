package com.ferreteriapsa.logistica.auth.config;

public class CustomUserPrincipal {

    private String username;
    private Long trabajadorId;
    private String rol;

    public CustomUserPrincipal(String username, Long trabajadorId, String rol) {
        this.username = username;
        this.trabajadorId = trabajadorId;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public Long getTrabajadorId() {
        return trabajadorId;
    }

    public String getRol() {
        return rol;
    }
}