package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Alias("member")
@Getter @Setter
public class Member {

    private Long memberId;
    private String account;
    private String fullName;
    private String pwd;
    private String email;
    private String phone;
    private String address;
    private String ssn;
    private LocalDateTime createdAt;
    private String role;

}
