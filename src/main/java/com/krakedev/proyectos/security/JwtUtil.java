package com.krakedev.proyectos.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET =
        "mi_secreto_super_seguro_2024";
    private static final long EXPIRATION =
        1000L * 60 * 60 * 8; // 8 horas

    private final Algorithm algorithm =
        Algorithm.HMAC256(SECRET);

    public String generarToken(String username,
                               String rol) {
        return JWT.create()
            .withSubject(username)
            .withClaim("rol", rol)
            .withIssuedAt(new Date())
            .withExpiresAt(
                new Date(System.currentTimeMillis()
                         + EXPIRATION))
            .sign(algorithm);
    }

    public DecodedJWT verificarToken(String token) {
        return JWT.require(algorithm)
                  .build()
                  .verify(token);
    }

    public String extraerUsername(String token) {
        return verificarToken(token).getSubject();
    }

    public String extraerRol(String token) {
        return verificarToken(token)
               .getClaim("rol").asString();
    }
}