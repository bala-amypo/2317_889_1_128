package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    //             String username = jwtTokenProvider.getUsernameFromToken(token);

    //             UsernamePasswordAuthenticationToken authentication =
    //                     new UsernamePasswordAuthenticationToken(
    //                             username,
    //                             null,
    //                             null
    //                     );

    //             SecurityContextHolder
    //                     .getContext()
    //                     .setAuthentication(authentication);
    //         }
    //     }

    //     filterChain.doFilter(request, response);
    // }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            if (jwtTokenProvider.validateToken(token)) {

                String username =
                        jwtTokenProvider.getUsernameFromToken(token);

                SimpleGrantedAuthority authority =
                        new SimpleGrantedAuthority("ROLE_ADMIN");

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of(authority)
                        );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
