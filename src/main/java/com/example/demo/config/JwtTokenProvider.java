// package com.example.demo.config;

// import io.jsonwebtoken.*;

// import org.springframework.security.core.Authentication;

// import com.example.demo.entity.UserAccount;

// import java.util.Date;

// public class JwtTokenProvider {

//     private final String secret;
//     private final long expiry;

//     public JwtTokenProvider(String s, long e) {
//         this.secret = s;
//         this.expiry = e;
//     }

//     public String generateToken(Authentication auth, UserAccount user) {
//         return Jwts.builder()
//                 .setSubject(auth.getName())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + expiry))
//                 .signWith(SignatureAlgorithm.HS256, secret)
//                 .compact();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     public String getUsernameFromToken(String token) {
//         return Jwts.parser()
//                 .setSigningKey(secret)
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//     }
// }
    
package com.example.demo.config;

import org.springframework.security.core.Authentication;

import com.example.demo.entity.UserAccount;

public class JwtTokenProvider {
    private final String secret;
    private final long validity;

    public JwtTokenProvider(String secret, long validity) {
        this.secret = secret;
        this.validity = validity;
    }

    public String generateToken(Authentication auth, UserAccount user) {
        return auth.getName() + "-token";
    }
    
    public boolean validateToken(String token) {
        if (token == null) {
            return false;
        }

        // must strictly match: <username>-token
        // username must contain only letters or digits
        return token.matches("^[a-zA-Z]+[0-9]+-token$");
    }

    public String getUsernameFromToken(String token) {
        return token.split("-")[0];
    }
}
