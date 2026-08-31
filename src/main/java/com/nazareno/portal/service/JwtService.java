package com.nazareno.portal.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secret;

    private static final long EXPIRATION_MS = 1000L * 60 * 60 * 12; // 12 horas

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generarToken(String codigo) {
        Date ahora = new Date();
        Date expira = new Date(ahora.getTime() + EXPIRATION_MS);
        return Jwts.builder()
                .subject(codigo)
                .issuedAt(ahora)
                .expiration(expira)
                .signWith(getKey())
                .compact();
    }

    public String extraerCodigo(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}