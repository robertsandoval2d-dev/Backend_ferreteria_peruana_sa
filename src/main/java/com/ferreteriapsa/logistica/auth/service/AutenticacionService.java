package com.ferreteriapsa.logistica.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ferreteriapsa.logistica.auth.model.*;
import com.ferreteriapsa.logistica.auth.repository.*;
import com.ferreteriapsa.logistica.auth.dto.request.UsuarioRequest;
import com.ferreteriapsa.logistica.auth.dto.response.AuthResponse;
import com.ferreteriapsa.logistica.auth.config.JwtService;

@Service
public class AutenticacionService implements AutenticacionInterface{
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public AutenticacionService(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public Usuario registrarUsuario(String username, String password, String userRol) {
        // 1. verificar si ya existe
        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        //2. crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));  //encriptar password (BCrypt)
        usuario.setActivo(true);
        
        Rol rol = rolRepository.findByNombre(userRol)
          .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.setRol(rol);

        // 3. guardar en BD (Hibernate)
        return usuarioRepository.save(usuario);

    }

    public AuthResponse login(UsuarioRequest request) {

        // 1. Buscar usuario
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Verificar si está activo
        if (!usuario.isActivo()) {
            throw new RuntimeException("Usuario deshabilitado");
        }

        // 3. Comparar contraseña (BCrypt)
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // 4. Generar token
         String token = jwtService.generateToken(usuario);

        // 5. Devolver token
        return new AuthResponse(token);

    }
}
