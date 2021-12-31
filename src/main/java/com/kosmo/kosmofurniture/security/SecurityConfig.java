package com.kosmo.kosmofurniture.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Order(2)
@Configuration
@EnableWebSecurity // web.xml에 org.springframework.web.filter.DelegatingFilterProxy라는 springSecurityFilterChain을 등록하는 것과 같다
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final SecurityExpressionHandler<FilterInvocation> roleExpressionHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) {

        web
                .ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers(
                        "/css/**",
                        "/js/**",
                        "/files/**",
                        "/fabicon.ico",
                        "/error"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/members/register").permitAll()
                .antMatchers("/products/**").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .expressionHandler(roleExpressionHandler)
        .and()
                .formLogin()
                .defaultSuccessUrl("/admin");
    }
}
