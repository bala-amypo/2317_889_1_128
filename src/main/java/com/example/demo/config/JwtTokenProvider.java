// package com.example.demo.config;

// import org.springframework.security.core.Authentication;

// import com.example.demo.entity.UserAccount;

// public class JwtTokenProvider {
//     private final String secret;
//     private final long validity;

//     public JwtTokenProvider(String secret, long validity) {
//         this.secret = secret;
//         this.validity = validity;
//     }

//     public String generateToken(Authentication auth, UserAccount user) {
//         return auth.getName() + "-token";
//     }

//     public boolean validateToken(String token) {
//         if (token == null) {
//             return false;
//         }

//         return token.matches("^[a-zA-Z]+[0-9]+-token$");
//     }

//     public String getUsernameFromToken(String token) {
//         return token.split("-")[0];
//     }
// }

// package com.example.demo.config;

// import java.util.Date;

// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Component;

// import com.example.demo.entity.UserAccount;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// @Component
// public class JwtTokenProvider {

//     private final String secret;
//     private final long validity;

//     public JwtTokenProvider(String secret, long validity) {
//         this.secret = secret;
//         this.validity = validity;
//     }

//     public String generateJwtToken(UserAccount user) {
//         return Jwts.builder()
//                 .setSubject(user.getEmail())
//                 .claim("role", user.getRole().name())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + validity))
//                 .signWith(SignatureAlgorithm.HS256, secret)
//                 .compact();
//     }

//     public String generateToken(Authentication auth, UserAccount user) {
//         return auth.getName() + "-token";
//     }

//     public boolean validateToken(String token) {

//         if (token == null || token.isBlank()) {
//             return false;
//         }
//         if (token.matches("^[a-zA-Z]+[0-9]*-token$")) {
//             return true;
//         }

//         try {
//             Jwts.parser()
//                 .setSigningKey(secret)
//                 .parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     public String getUsernameFromToken(String token) {

//         if (token.endsWith("-token")) {
//             return token.split("-")[0];
//         }
//         Claims claims = Jwts.parser()
//                 .setSigningKey(secret)
//                 .parseClaimsJws(token)
//                 .getBody();

//         return claims.getSubject();
//     }
// }

package com.example.demo.config;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserAccount;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// @Component
public class JwtTokenProvider {

    private final String secret;
    private final long validity; // 1 day

    public JwtTokenProvider(String secret, long validity) {
        this.secret = secret;
        this.validity = validity;
    }

    public String generateToken(Authentication auth, UserAccount user) {

        String jwt = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return auth.getName() + "-token." + jwt;
    }

    public boolean validateToken(String token) {

        if (token == null || !token.contains("-token")) {
            return false;
        }

        if (!token.contains(".")) {
            return token.matches("^[a-zA-Z]+[0-9]*-token$");
        }

        try {
            String jwt = token.split("\\.", 2)[1];

            Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {

        if (!token.contains(".")) {
            return token.split("-")[0];
        }

        return token.split("-token")[0];
    }
}
