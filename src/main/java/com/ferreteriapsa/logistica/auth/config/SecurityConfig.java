package com.ferreteriapsa.logistica.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 🔴 desactivar CSRF (para APIs REST)
            .csrf(csrf -> csrf.disable())

            // 🔐 configuración de rutas
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/logistica/auth/login").permitAll() // público
                .requestMatchers("/logistica/trabajadores/registrar").hasRole("ADMIN")
                .anyRequest().authenticated()         // protegido
            )
            .formLogin(form -> form.disable())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // sin login HTML

        return http.build();
    }
}