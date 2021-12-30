package com.kosmo.kosmofurniture.security;

import com.kosmo.kosmofurniture.domain.ApiMember;
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

        ApiMember apiMember = new ApiMember(member.getAccount(), member.getPwd(), roles);
        apiMember.setEmail(member.getEmail());
        apiMember.setFullName(member.getFullName());
        apiMember.setThumbnailUrl(member.getThumbnailUrl());

        return apiMember;


    }

}