package com.ferreteriapsa.logistica.auth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.ferreteriapsa.logistica.auth.model.Usuario;
import com.ferreteriapsa.logistica.auth.repository.UsuarioRepository;
import java.io.IOException;
import java.util.List;
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // 1. Verificar que exista el token
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extraer token
        String token = header.substring(7);

        // 3. Validación del token
        if (jwtService.isTokenValid(token)) {

            String username = jwtService.extractUsername(token);

            Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);

            if (usuario != null) {

                String rol = jwtService.extractRol(token);

                var authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_" + rol)
                );
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(usuario, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // 4. Continuar la petición
        filterChain.doFilter(request, response);
    }
}
