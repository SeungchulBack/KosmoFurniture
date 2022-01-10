package com.kosmo.kosmofurniture.jwt;

import com.kosmo.kosmofurniture.domain.MemberPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
@Slf4j
public class TokenProvider {

    private final String secret = "sdlkdvfjoilchlk2hlw34clikASDLUISAD98cf235liuhsdf98ASLKSHDliu534klFKgh245likusdf98";
    private final long tokenValidityInMilliseconds = 1000 * 60 * 60 ; // 1시간

    private byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
    private Key key = Keys.hmacShaKeyFor(keyBytes);

    public String createToken(Authentication authentication) {

        log.debug(authentication.toString());

        MemberPrincipal memberPrincipal = (MemberPrincipal) authentication.getPrincipal();

        // role에 [ROLE_USER] 이런식으로 [] 대괄호 있으면 권한으로 넘어가는 값이 ROLE_USER가 아니라 [ROLE_USER]로 넘어가서 오류남. 대괄호[]잘라내야함
        String role = authentication.getAuthorities().toString();
        role = StringUtils.strip(role, "[");
        role = StringUtils.strip(role, "]");

        log.debug(role);

        long now = (new Date()).getTime();

        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        Claims claims = Jwts.claims()
                .setExpiration(validity);

        claims.put("memberId", memberPrincipal.getMemberId());
        claims.put("account", memberPrincipal.getAccount());
        claims.put("fullName", memberPrincipal.getFullName());
        claims.put("email", memberPrincipal.getEmail());
        claims.put("role", role);
        claims.put("thumbnailUrl", memberPrincipal.getThumbnailUrl());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        List<GrantedAuthority> roles = new ArrayList<>();
        String role = claims.get("role").toString();
        roles.add(new SimpleGrantedAuthority(role));

        MemberPrincipal principal = new MemberPrincipal(claims.get("account").toString(), "", roles);
        principal.setMemberId(Long.valueOf(String.valueOf(claims.get("memberId")))); // claim.get()값이 Integer로 나와서 좀 지저분해졌다.

        log.debug("principal : {}", principal);

        return new UsernamePasswordAuthenticationToken(principal, "", roles);
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SignatureException | MalformedJwtException e) {
            log.debug("잘못된 JWT 서명입니다");
        } catch (ExpiredJwtException e) {
            log.debug("만료된 JWT 토큰입니다");
        } catch (UnsupportedJwtException e) {
            log.debug("지원되지 않는 JWT 토큰입니다");
        } catch (IllegalArgumentException e) {
            log.debug("JWT 토큰이 잘못되었습니다");
        }
        return false;
    }
}
