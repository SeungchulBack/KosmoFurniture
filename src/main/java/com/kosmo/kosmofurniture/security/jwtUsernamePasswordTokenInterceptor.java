package com.kosmo.kosmofurniture.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
public class jwtUsernamePasswordTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.debug("Authentication : {}", Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).map(Authentication::toString).orElse("null"));
        log.debug("authority : {}", Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).map(Authentication::getAuthorities).map(grantedAuthorities -> grantedAuthorities.toString()).orElse("null"));

        return true;
    }
}
