package com.unicarioca.portal.controller.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtCreator {

    private static final String SECRET_KEY = "m1Q7+B8xTcMqE6zi1SvPSaCnPI3aSwpOoZfbjW2mr/U=";
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hora

    private final Logger log = LoggerFactory.getLogger(JwtCreator.class);

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Gera o token JWT
    public String generateToken(String username) {
        log.info("Crinaod token para usuario: " + username);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Valida o token JWT
    public boolean validateToken(String token) {
        log.info("Validando token");
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Token inválido ou expirado
        }
    }

    // Extrai o usuário do token JWT
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
