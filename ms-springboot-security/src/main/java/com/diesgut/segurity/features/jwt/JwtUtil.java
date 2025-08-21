package com.diesgut.segurity.features.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expirationTime;

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(UserDetails details) {
        //en el body del token se incluye el usuario
        //y los roles a los que pertenece, además
        //de la fecha de caducidad y los datos de la firma
        return Jwts.builder()
                .subject(details.getUsername()) //usuario
                .issuedAt(new Date())
                .claim("authorities", details.getAuthorities().stream() //roles
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime)) //fecha caducidad
                .signWith(key)//clave y algoritmo para firma
                .compact(); //generación del token

    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (
                username.equals(userDetails.getUsername()) && !isTokenExpired(token)
        );
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token.replace("Bearer ", ""))
                .getPayload();
    }
}
