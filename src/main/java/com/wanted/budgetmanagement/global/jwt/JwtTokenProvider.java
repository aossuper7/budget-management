package com.wanted.budgetmanagement.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

@Component
public class JwtTokenProvider implements InitializingBean {
    private final String access;
    private final String refresh;
    private final long accessValidity;
    private final long refreshValidity;
    private Key accessSecretKey;
    private Key refreshSecretKey;

    public JwtTokenProvider(
            @Value("${jwt.access}") String access,
            @Value("${jwt.refresh}") String refresh,
            @Value("${jwt.access-validity-in-seconds}") long accessValidity,
            @Value("${jwt.refresh-validity-in-seconds}") long refreshValidity) {
        this.access = access;
        this.refresh = refresh;
        this.accessValidity = accessValidity * 1000;
        this.refreshValidity = refreshValidity * 1000;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(access);
        this.accessSecretKey = Keys.hmacShaKeyFor(keyBytes);
        keyBytes = Decoders.BASE64.decode(refresh);
        this.refreshSecretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String username) {
        return buildJwtToken(username, accessSecretKey, accessValidity);
    }

    public String createRefreshToken(String username) {
        return buildJwtToken(username, refreshSecretKey, refreshValidity);
    }

    private String buildJwtToken(String username, Key secretKey, long validityPeriod) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + validityPeriod);

        return Jwts.builder()
                .setSubject(username)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(accessSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        User principal = new User(claims.getSubject(), "N/A", new ArrayList<>());

        return new UsernamePasswordAuthenticationToken(principal, token, new ArrayList<>());
    }

    public void validateToken(String token) {
        Jwts.parserBuilder().setSigningKey(accessSecretKey).build().parseClaimsJws(token);
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(refreshSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
