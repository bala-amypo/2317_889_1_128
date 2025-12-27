// package com.example.demo.config;

// import java.nio.charset.StandardCharsets;
// import java.util.Date;

// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Component;

// import com.example.demo.entity.UserAccount;

// import io.jsonwebtoken.Claims; 
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;

// @Component
// public class JwtTokenProvider {

//     private final String secret;
//     private final long validity;

//     public JwtTokenProvider() {
//         this.secret = "this-is-a-very-secure-32-byte-jwt-secret-key";
//         this.validity = 86400000;
//     }

//     public JwtTokenProvider(String secret, long validity) {
//         this.secret = secret;
//         this.validity = validity;
//     }

//     public String generateToken(Authentication auth, UserAccount user) {

//         String jwt = Jwts.builder()
//                 .setSubject(user.getEmail())
//                 .claim("role", user.getRole().name())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + validity))
//                 .signWith(
//                     Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))
//                 )
//                 .compact();

//         return auth.getName() + "-token." + jwt;
//     }

//     public boolean validateToken(String token) {

//         if (token == null || !token.contains("-token")) {
//             return false;
//         }

//         if (!token.contains(".")) {
//             return token.matches("^[a-zA-Z]+[0-9]+-token$");
//         }

//         try {
//             String jwt = token.split("\\.", 2)[1];

//             Jwts.parserBuilder()
//                 .setSigningKey(
//                     Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))
//                 )
//                 .build()
//                 .parseClaimsJws(jwt);

//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     public String getUsernameFromToken(String token) {

//         if (!token.contains(".")) {
//             return token.split("-")[0];
//         }
        
//         return token.split("-token")[0];
//     }

//     public String getRoleFromToken(String token) {
//         if (!token.contains(".")) return null;

//         Claims claims = Jwts.parserBuilder()
//             .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
//             .build()
//             .parseClaimsJws(token.split("\\.",2)[1])
//             .getBody();

//         return claims.get("role", String.class);
//     }

// }


package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    
    private final String secret;
    private final long validityInMs;
    private final Key key;
    
    public JwtTokenProvider() {
        this.secret = "MySecretKeyForJWTTokenGenerationMustBeLongEnough1234567890";
        this.validityInMs = 3600000; // 1 hour
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    
    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    
    public String generateToken(String email, String role, Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("userId", userId);
        
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMs);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    
    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("role");
    }
    
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.valueOf(claims.get("userId").toString());
    }
}