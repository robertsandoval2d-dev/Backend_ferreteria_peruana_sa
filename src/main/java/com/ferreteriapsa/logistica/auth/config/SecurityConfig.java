package com.ferreteriapsa.logistica.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; //ADDED
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration; //ADDED
import org.springframework.security.config.Customizer; //ADDED
import org.springframework.web.cors.CorsConfigurationSource; //ADDED
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; //ADDED
import java.util.Arrays; //ADDED

@Configuration
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 1. VITAL: Activar CORS a nivel de Spring Security
            .cors(Customizer.withDefaults()) //ADDED

            // 🔴 desactivar CSRF (para APIs REST)
            .csrf(csrf -> csrf.disable())

            // 🔐 configuración de rutas
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() //ADDED
                .requestMatchers("/logistica/auth/login","/error").permitAll() // público
                .requestMatchers("/logistica/trabajadores/registrar").hasRole("ADMIN")
                .anyRequest().authenticated()         // protegido
            )
            .formLogin(form -> form.disable())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // sin login HTML

        return http.build();
    }

    @Bean //ADDED
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permite tu Frontend
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); 
        // Permite los métodos que usa Angular
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Permite las cabeceras estándar y el envío del Token
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica esto a toda la API
        return source;
    }
}
