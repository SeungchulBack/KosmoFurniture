package com.kosmo.kosmofurniture.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class AuthenticationLogFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).map(Authentication::toString).orElse("null");
        log.debug("authentication : {}",authentication);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}