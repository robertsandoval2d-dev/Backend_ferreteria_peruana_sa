package com.ferreteriapsa.logistica.auth.config;

import com.ferreteriapsa.logistica.auth.model.*;
import com.ferreteriapsa.logistica.auth.repository.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class DataInitializer {
    @Value("${admin.initializer.password}")
    private String ADMIN_PASSWORD;

    @Bean
    CommandLineRunner init(UsuarioRepository userRepository,RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            if (userRepository.findByUsername("admin").isEmpty()) {

                Rol adminRol = rolRepository.findByNombre("ADMIN")
                        .orElseGet(() -> {
                            Rol r = new Rol();
                            r.setNombre("ADMIN");
                            return rolRepository.save(r);
                        });

                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
                admin.setRol(adminRol);

                userRepository.save(admin);
            }

        };
    }
}