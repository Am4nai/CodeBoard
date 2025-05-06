package com.codeboard.codeboard_backend.security;

import com.codeboard.codeboard_backend.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization header: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println("Extracted token: " + token);
            if (jwtUtils.validateToken(token)) {
                System.out.println("Token is valid, username: " + jwtUtils.getUsernameFromToken(token));
                String username = jwtUtils.getUsernameFromToken(token);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Authentication set for: " + username);
            } else {
                System.out.println("Token is invalid");
            }
        } else {
            System.out.println("No valid Authorization header found");
        }

        filterChain.doFilter(request, response);
    }
}
