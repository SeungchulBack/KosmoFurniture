package com.kosmo.kosmofurniture.service;

import com.kosmo.kosmofurniture.domain.Member;
import com.kosmo.kosmofurniture.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    private final String secret = "kimParkBeak";

    public List<Member> getAllMembers() {
        return memberMapper.findAll();
    }

    public Long createMember(Member member) {
        String encodedPassword = passwordEncoder.encode(member.getPwd());
        member.setPwd(encodedPassword);
        member.setCreatedAt(LocalDateTime.now());
        Long savedId = member.getMemberId();
        return savedId;
    }

    public Member createMemberWithSocialLogin(String socialId, String name) {
        String encodedPassword = passwordEncoder.encode(socialId + secret);
        Member member = new Member();
        member.setAccount(socialId);
        member.setPwd(encodedPassword);
        member.setFullName(name);
        member.setRole("ROLE_USER");
        member.setCreatedAt(LocalDateTime.now());
        memberMapper.save(member);
        return member;
    }

    public Member getSingleMember(Long memberId) {
        return memberMapper.findById(memberId);
    }

    public Member getMemberBySsn(String ssn) {
        return memberMapper.findBySsn(ssn);
    }
}
