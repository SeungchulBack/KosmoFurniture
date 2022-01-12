package com.kosmo.kosmofurniture.security;

import com.kosmo.kosmofurniture.jwt.JwtAccessDeniedHandler;
import com.kosmo.kosmofurniture.jwt.JwtAuthenticationEntryPoint;
import com.kosmo.kosmofurniture.jwt.JwtAuthenticationFilter;
import com.kosmo.kosmofurniture.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Order(1)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final SecurityExpressionHandler<FilterInvocation> roleExpressionHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .antMatcher("/api/**") //이걸 설정하지 않으면 SecurityConfig 클래스로 요청이 넘어가지 않는다.
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .expressionHandler(roleExpressionHandler)
                .antMatchers("/api/products/**").hasRole("ANONYMOUS")
                .antMatchers("/api/hello").hasRole("ANONYMOUS")
                .antMatchers("/api/hi").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/register").permitAll()

                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new AuthenticationLogFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
