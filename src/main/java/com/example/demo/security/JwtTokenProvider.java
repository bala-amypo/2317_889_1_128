// package com.example.demo.security;

// import io.jsonwebtoken.*;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Component;

// import java.util.Date;

// @Component
// public class JwtTokenProvider {

//     private final String SECRET = "test-secret-key";
//     private final long EXPIRATION = 3600000; // 1 hour

//     public String generateToken(Authentication authentication) {

//         CustomUserDetails user =
//                 (CustomUserDetails) authentication.getPrincipal();

//         return Jwts.builder()
//                 .setSubject(user.getUsername())
//                 .claim("userId", user.getId())
//                 .claim("role", user.getAuthorities().iterator().next().getAuthority())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//                 .signWith(SignatureAlgorithm.HS256, SECRET)
//                 .compact();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     public Long getUserIdFromToken(String token) {
//         Claims claims = getClaims(token);
//         return claims.get("userId", Long.class);
//     }

//     public String getEmailFromToken(String token) {
//         return getClaims(token).getSubject();
//     }

//     public String getRoleFromToken(String token) {
//         return getClaims(token).get("role", String.class);
//     }

//     private Claims getClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(SECRET)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }
// }
