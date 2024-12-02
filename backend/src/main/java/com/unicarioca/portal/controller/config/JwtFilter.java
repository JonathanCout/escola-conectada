package com.unicarioca.portal.controller.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtCreator jwtCreator;
    private final PasswordEncoder passwordEncoder;

    private final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    public JwtFilter(JwtCreator jwtCreator, PasswordEncoder passwordEncoder) {
        this.jwtCreator = jwtCreator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Headers: " + request.getHeaderNames());
        String authorization = request.getHeader("Authorization");
        log.info("Rodando filtro interno de autenticacao");
        // Essa parte é para autorizar no caso do /login
        if (request.getServletPath().equals("/login")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = authorization.substring(7); // Pula o 'Bearer ' e pega só o token em si
        if (!jwtCreator.validateToken(token)){
            log.warn("Token inválido ou expirado");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token inválido ou expirado");
            return;
        }

        String user = jwtCreator.extractUsername(token);
        UserDetails userDetails = userDetailsService().loadUserByUsername(user);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        log.info("Usuario autenticado: " + user);
        filterChain.doFilter(request,response);

    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(
                User.withUsername("ALUNO")
                        .password(passwordEncoder.encode("admin"))
                        .roles("ADMIN")
                        .build(),
                User.withUsername("PROFESSOR")
                        .password(passwordEncoder.encode("admin"))
                        .roles("ADMIN")
                        .build()
        );
    }
}
