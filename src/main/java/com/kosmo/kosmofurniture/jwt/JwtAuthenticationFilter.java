package com.kosmo.kosmofurniture.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private TokenProvider tokenProvider;

    public JwtAuthenticationFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        String urlToken = servletRequest.getParameter("token");
        log.debug("URL param token : {}", urlToken);

        boolean tokenNull = urlToken != null;
        log.debug("tokenNull : {}", tokenNull);

        if (StringUtils.hasText(jwt) || urlToken != null) {

            String token = jwt == null ? urlToken : jwt;
            tokenProvider.validateToken(token);
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 {} 인증정보를 저장했습니다 ", SecurityContextHolder.getContext().getAuthentication());
            log.debug("Requested URI : {}", requestURI);

            filterChain.doFilter(httpServletRequest, servletResponse);
        } else {
            log.debug("유효한 JWT 토큰이 없습니다, URI: {}", requestURI);
            filterChain.doFilter(httpServletRequest, servletResponse);
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        log.debug(bearerToken);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
