package com.example.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserAccount;

@Component
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

        return token.matches("^[a-zA-Z]+[0-9]+-token$");
    }

    public String getUsernameFromToken(String token) {
        return token.split("-")[0];
    }
}
