package com.codewithsaadh.medivaultbackend.method;

import com.codewithsaadh.medivaultbackend.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("username", user.getUsername());
        claims.put("userType", user.getUserType().getValue());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public User extractUserFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        // Create and return the User object from the extracted claims
        Long userId = Long.parseLong(claims.get("userId", String.class));
        String username = claims.get("username", String.class);
        User.UserType userType = User.UserType.valueOf(claims.get("userType", String.class).toUpperCase());

        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setUserType(userType);

        return user;
    }

    // Add methods to validate token, check expiration, etc. if needed
}
