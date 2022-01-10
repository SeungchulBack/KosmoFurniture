package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter @Setter
@ToString(callSuper = true)
public class MemberPrincipal extends User {

    private Long memberId;
    private String account;
    private String fullName;
    private String email;
    private String thumbnailUrl;

    public MemberPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
