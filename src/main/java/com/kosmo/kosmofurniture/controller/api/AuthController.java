package com.kosmo.kosmofurniture.controller.api;

import com.kosmo.kosmofurniture.domain.MemberPrincipal;
import com.kosmo.kosmofurniture.domain.LoginDto;
import com.kosmo.kosmofurniture.domain.Member;
import com.kosmo.kosmofurniture.domain.TokenDto;
import com.kosmo.kosmofurniture.jwt.JwtAuthenticationFilter;
import com.kosmo.kosmofurniture.jwt.TokenProvider;
import com.kosmo.kosmofurniture.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberMapper memberMapper;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("authentication : {}", authentication);
        return ResponseEntity.ok("hello");
    }
    @GetMapping("/hi")
    public ResponseEntity<String> hi() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("authentication : {}", authentication);
        return ResponseEntity.ok("hi");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {

        log.debug("loginDto account : {}", loginDto.getAccount());

        Member member = memberMapper.findByAccount(loginDto.getAccount());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getAccount(), loginDto.getPwd());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.debug("ApiMember 이름 : {}", ((MemberPrincipal) authentication.getPrincipal()).getFullName());

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
