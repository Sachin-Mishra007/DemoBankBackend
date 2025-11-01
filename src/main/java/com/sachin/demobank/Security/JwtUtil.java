package com.sachin.demobank.Security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
    private final Key signingKey;
    private final long jwtExpirationMs;

    public JwtUtil(@Value("${app.jwt.secret}") String secret,
        @Value("${app.jwt.expiration-ms:3600000}") long jwtExpirationMs
    )
    {
        if(secret==null||secret.length()<32)
        {
            throw new IllegalArgumentException("Jwt secret ust be set and atleast 32 chars");

        }
        this.signingKey=Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpirationMs=jwtExpirationMs;
    }

    public String generateToken(String username,String role)
    {
        Date now=new Date();
        Date expiry= new Date(now.getTime()+jwtExpirationMs);
        return Jwts.builder()
        .setSubject(username)
        .claim("role", role)
        .setIssuedAt(now)
        .setExpiration(expiry)
        .signWith(signingKey,SignatureAlgorithm.HS256)
        .compact();
    }

    public boolean validateToken(String token)
    {
        try{
            Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
            return true;
        }
        catch(JwtException ex)
        {
            return false;
        }
    }

    public String getUsernameFromToken(String token)
    {
        Claims claims=Jwts.parserBuilder().setSigningKey(signingKey).build()
        .parseClaimsJws(token)
        .getBody();
        return claims.getSubject();
    }

    public String getRoleFromToken(String token)
    {
        Claims claims=Jwts.parserBuilder().setSigningKey(signingKey).build()
        .parseClaimsJws(token)
        .getBody();

        return claims.get("role", String.class);
    }

}
