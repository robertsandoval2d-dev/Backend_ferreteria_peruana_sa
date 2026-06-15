package com.ferreteriapsa.logistica.auth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import io.jsonwebtoken.ExpiredJwtException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.List;
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @SuppressWarnings("null")
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        // Le decimos al filtro que NO se ejecute en estas rutas
        return path.contains("/logistica/auth/login") || 
               path.contains("/logistica/auth/refresh") ||
               path.contains("/logistica/auth/logout");
    }

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

        try {
            // 3. Al intentar extraer el username, JJWT lanzará una excepción si el token expiró
            String username = jwtService.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                Long trabajadorId = jwtService.extractTrabajadorId(token);
                String rol = jwtService.extractRol(token);

                var authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_" + rol.toUpperCase())
                );

                CustomUserPrincipal principal = new CustomUserPrincipal(username, trabajadorId, rol);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(principal, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            // 4. Continuar la petición si todo es válido
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"El token ha expirado. Por favor, inicia sesión nuevamente o usa tu Refresh Token.\"}");
            return; 
            
        } catch (Exception e) {

//            response.getWriter().write("{\"error\": \"Token inválido o manipulado.\"}");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"error\": \"Token inválido o manipulado.\", " +
                            "\"detalle\": \"" + e.getMessage().replace("\"", "'") + "\"}"
            );
            return;
        }
    }
}
