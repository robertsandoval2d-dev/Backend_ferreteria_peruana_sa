package com.ferreteriapsa.logistica.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.ferreteriapsa.logistica.auth.service.CustomUserDetailService;

@Configuration
public class SecurityConfig {
    private final CustomUserDetailService userDetailsService;

    public SecurityConfig(CustomUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 🔴 desactivar CSRF (para APIs REST)
            .csrf(csrf -> csrf.disable())

            // 🔐 configuración de rutas
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/logistica/auth/**").permitAll() // público
                .anyRequest().authenticated()           // protegido
            )
            .userDetailsService(userDetailsService)
            .formLogin(form -> form.disable()); // 🔥 sin login HTML

        return http.build();
    }
}