package com.example.ms_hotel.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET =
            "MiClaveSuperSecretaParaJWT2026ProyectoHotel";

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 30)
                )
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // NUEVO: Método para extraer el usuario del token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // NUEVO: Método para validar que el token es correcto y no ha expirado
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false; // Si hay error (caducado, firma inválida), retorna falso
        }
    }

    // NUEVO: Método interno para decodificar el token usando la versión 0.12.x de JJWT
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}