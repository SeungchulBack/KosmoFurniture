package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter @Setter
public class ApiMember extends User {

    private String fullName;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime createdAt;
    private String thumbnailUrl;

    public ApiMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
