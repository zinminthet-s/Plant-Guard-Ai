package com.zinminthet.plantguardai.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private final static String secret = Encoders.BASE64.encode("plantguardaiapplicationthawkhantzin".getBytes(StandardCharsets.UTF_8));


    public static String generate(String email, String role, Long userId) throws JwtException{

        Map<String, String> info = new HashMap<>();
        info.put("email", email);
        info.put("role", role.split("ROLE_")[1]);
        info.put("userId", String.format("%d", userId));

        return Jwts.builder()
                .subject("Jwt token")
                .issuer("PGA organization")
                .claims(info)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey())
                .compact();

    }

    public static Claims validate(String jwtToken) throws JwtException {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();

    }


    private static SecretKey getKey(){
        var bytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }
}
