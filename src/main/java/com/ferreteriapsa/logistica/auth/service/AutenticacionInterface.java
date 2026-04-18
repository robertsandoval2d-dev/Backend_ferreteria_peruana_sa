package com.ferreteriapsa.logistica.auth.service;

import com.ferreteriapsa.logistica.auth.model.Usuario;

public interface AutenticacionInterface {
    Usuario registrarUsuario(String username, String password, String userRol);
}
