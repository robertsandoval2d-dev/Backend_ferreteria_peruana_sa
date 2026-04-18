package com.ferreteriapsa.logistica.auth.dto.response;

public class UsuarioDTO {
    private String username;
    private String rol;

    public UsuarioDTO() {}

    public UsuarioDTO(String username, String rol) {
        this.username = username;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
