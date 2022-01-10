package com.kosmo.kosmofurniture.security;

import com.kosmo.kosmofurniture.domain.MemberPrincipal;
import com.kosmo.kosmofurniture.domain.Member;
import com.kosmo.kosmofurniture.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Member member = memberMapper.findByAccount(account);

        if (member == null) throw new UsernameNotFoundException(account);

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(member.getRole()));

        MemberPrincipal memberPrincipal = new MemberPrincipal(member.getAccount(), member.getPwd(), roles);
        memberPrincipal.setMemberId(member.getMemberId());
        memberPrincipal.setAccount(member.getAccount());
        memberPrincipal.setEmail(member.getEmail());
        memberPrincipal.setFullName(member.getFullName());
        memberPrincipal.setThumbnailUrl(member.getThumbnailUrl());

        return memberPrincipal;


    }

}