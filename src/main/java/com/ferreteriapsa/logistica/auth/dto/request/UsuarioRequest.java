package com.ferreteriapsa.logistica.auth.dto.request;

public class UsuarioRequest {
    
    private String username;
    private String password;


    public UsuarioRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
