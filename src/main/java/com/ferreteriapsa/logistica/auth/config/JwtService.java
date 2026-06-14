package com.ferreteriapsa.logistica.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.ferreteriapsa.logistica.auth.model.Usuario;

@Service
public class JwtService {
    //  Mínimo 32 caracteres
    @Value("${jwt.secret}")
    private String SECRET;

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(Usuario usuario) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim("rol", usuario.getRol().getNombre())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 900000)) //15 min * 60 seg * 1000 miliseg
                .signWith(getKey());

        if (usuario.getTrabajador() != null) {
            builder.claim("trabajadorId", usuario.getTrabajador().getTrabajadorId());
            builder.claim("nombre", usuario.getTrabajador().getNombre());
            if ("JEFE_DE_LINEA".equals(usuario.getRol().getNombre()) && 
                usuario.getTrabajador().getAsignaciones() != null) {
                usuario.getTrabajador().getAsignaciones().stream()
                    .filter(asignacion -> asignacion.isActivo())
                    .filter(asignacion -> asignacion.getLineaProducto() != null)
                    .findFirst()
                    .ifPresent(asignacionActiva ->{
                        builder.claim("linea", asignacionActiva.getLineaProducto().getNombre());
                    });
            }
        }

        return builder.compact();
    }

    public String generateRefreshToken(Usuario usuario){
        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim("type", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day (1 * 24 * 60 * 60 * 1000)
                .signWith(getKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRol(String token) {
        return extractClaim(token, claims -> claims.get("rol", String.class));
    }

    public Long extractTrabajadorId(String token) {
        return extractClaim(token, claims -> claims.get("trabajadorId", Long.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return resolver.apply(claims);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
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

    public boolean isTokenValid(String token, Usuario usuario){
        final String username = extractUsername(token);
        return(username.equals(usuario.getUsername())) && !isTokenExpired(token);
    }

    public boolean isRefreshToken(String token) {
        String type = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("type", String.class);

        return "refresh".equals(type);
    }

}
