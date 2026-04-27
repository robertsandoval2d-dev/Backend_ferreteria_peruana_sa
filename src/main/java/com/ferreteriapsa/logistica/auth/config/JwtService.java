package com.ferreteriapsa.logistica.auth.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.ferreteriapsa.logistica.auth.model.Usuario;

@Service
public class JwtService {
    // 🔑 Mínimo 32 caracteres
    @Value("${jwt.secret}")
    private String SECRET;

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // public String generateToken(Usuario usuario) {
    //     return Jwts.builder()
    //             .setSubject(usuario.getUsername())
    //             .claim("rol", usuario.getRol().getNombre())
    //             .claim("trabajadorId",usuario.getTrabajador().getId())
    //             .setIssuedAt(new Date())
    //             .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
    //             .signWith(getKey())
    //             .compact();
    // }
    public String generateToken(Usuario usuario) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim("rol", usuario.getRol().getNombre())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))//1 día
                .signWith(getKey());

        if (usuario.getTrabajador() != null) {
            builder.claim("trabajadorId", usuario.getTrabajador().getId());
        }

        return builder.compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String extractRol(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("rol", String.class);
    }

    public Long extractTrabajadorId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("trabajadorId", Long.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
