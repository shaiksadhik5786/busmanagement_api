package com.web.sms.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

    // Read secret key from application.properties
    @Value("${jwt.secret}")
    private String secret;

    // Token validity: 30 minutes
    private final long jwtExpiration = 1000 * 60 * 30;

    // Create signing key
    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // =====================================================
    // 1. GENERATE JWT TOKEN
    // =====================================================

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    // =====================================================
    // 2. EXTRACT USERNAME FROM TOKEN
    // =====================================================

    public String extractUsername(String token) {

        return extractClaim(token,Claims::getSubject);
    }

    // =====================================================
    // 3. EXTRACT ANY CLAIM
    // =====================================================

    private <T> T extractClaim(String token,Function<Claims, T> claimsResolver) {

        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claimsResolver.apply(claims);
    }

    // =====================================================
    // 4. EXTRACT EXPIRATION DATE
    // =====================================================

    private Date extractExpiration(String token) {

        return extractClaim(token,Claims::getExpiration);
    }

    // =====================================================
    // 5. CHECK TOKEN EXPIRATION
    // =====================================================

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    // =====================================================
    // 6. VALIDATE TOKEN
    // =====================================================

    public boolean validateToken(String token,UserDetails userDetails) {

        try {

            String username = extractUsername(token);

            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

        } catch (Exception e) {

            return false;
        }
    }

    // =====================================================
    // 7. VALIDATE TOKEN WITHOUT USERDETAILS
    // =====================================================

    public boolean tokenValidation(String token) {

        try {

            extractClaim(token, Claims::getSubject);

            return !isTokenExpired(token);

        } catch (Exception e) {

            return false;
        }
    }
}
