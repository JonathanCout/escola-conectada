package com.unicarioca.portal.controller.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtCreator jwtCreator;

    public JwtFilter(JwtCreator jwtCreator) {
        this.jwtCreator = jwtCreator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        // Essa parte é para autorizar no caso do /login
        if (authorization == null || !authorization.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = authorization.substring(7); // Pula o 'Bearer ' e pega só o token em si
        if (!jwtCreator.validateToken(token)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token inválido ou expirado");
            return;
        }

        String user = jwtCreator.extractUsername(token);
        request.setAttribute("usuario", user);
        filterChain.doFilter(request,response);

    }
}
