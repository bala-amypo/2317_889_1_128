package com.example.demo.security;

public class JwtUtil {

    String secret;
    long validityInMs;

    public JwtUtil(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }    
}
