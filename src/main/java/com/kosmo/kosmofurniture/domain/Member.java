package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long member_id;
    private String account;
    private String pwd;
    private String email;
    private String phone;
    private String city;

}
