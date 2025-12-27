package com.example.demo.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // @Override
    // protected void doFilterInternal(
    //         HttpServletRequest request,
    //         HttpServletResponse response,
    //         FilterChain filterChain)
    //         throws ServletException, IOException {

    //     String header = request.getHeader("Authorization");

    //     if (header != null && header.startsWith("Bearer ")) {

    //         String token = header.substring(7);

    //         if (jwtTokenProvider.validateToken(token)) {

    //             String username =
    //                     jwtTokenProvider.getUsernameFromToken(token);

    //             String role = jwtTokenProvider.getRoleFromToken(token);

    //             SimpleGrantedAuthority authority =
    //                 new SimpleGrantedAuthority("ROLE_" + role);


    //             UsernamePasswordAuthenticationToken authentication =
    //                     new UsernamePasswordAuthenticationToken(
    //                             username,
    //                             null,
    //                             List.of(authority)
    //                     );

    //             SecurityContextHolder
    //                     .getContext()
    //                     .setAuthentication(authentication);
    //         }
    //     }

    //     filterChain.doFilter(request, response);
    // }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                   HttpServletResponse response, 
                                   FilterChain filterChain) 
            throws ServletException, IOException {
        
        String token = extractToken(request);
        
        if (token != null && jwtTokenProvider.validateToken(token)) {
            String email = jwtTokenProvider.getEmailFromToken(token);
            String role = jwtTokenProvider.getRoleFromToken(token);
            
            UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(
                            email, 
                            null, 
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
                    );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        filterChain.doFilter(request, response);
    }
    
    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
