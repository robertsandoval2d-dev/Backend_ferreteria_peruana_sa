package com.ferreteriapsa.logistica.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ferreteriapsa.logistica.auth.dto.request.*;
import com.ferreteriapsa.logistica.auth.dto.response.*;
import com.ferreteriapsa.logistica.auth.service.AutenticacionService;

@CrossOrigin(origins = "http://localhost:4200") // ADDED
@RestController
@RequestMapping("/logistica/auth")
public class AutenticacionController {
    private final AutenticacionService autenticacionService;

    public AutenticacionController(AutenticacionService autenticacionService) {
        this.autenticacionService = autenticacionService;
    }

    @SuppressWarnings("null")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UsuarioRequest request) {
        Auth credenciales = autenticacionService.login(request);

        ResponseCookie cookie = ResponseCookie.from(
                "refresh_token",
                credenciales.getRefreshToken())
                .httpOnly(true)
                .secure(false) // false en localhost
                .path("/")
                .maxAge(86400) //1 dia * 24 * 60 * 60
                .sameSite("Lax")
                .build();

        AuthResponse token = new AuthResponse(credenciales.getToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE,cookie.toString())
                .body(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@CookieValue("refresh_token") String refreshToken) {
        AuthResponse credenciales = autenticacionService.refreshToken(refreshToken);

        return ResponseEntity.ok(credenciales);
    } 

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {

        ResponseCookie cookie = ResponseCookie.from(
                "refresh_token",
                "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok()
                .header(
                    HttpHeaders.SET_COOKIE,
                    cookie.toString()
                )
                .build();
    }

}
