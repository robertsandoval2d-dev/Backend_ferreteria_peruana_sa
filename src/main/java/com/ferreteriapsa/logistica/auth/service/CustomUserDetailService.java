package com.ferreteriapsa.logistica.auth.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.ferreteriapsa.logistica.auth.model.Usuario;
import com.ferreteriapsa.logistica.auth.repository.UsuarioRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(usuario.getRol().name())
                .disabled(!usuario.isActivo())
                .build();
    }
}