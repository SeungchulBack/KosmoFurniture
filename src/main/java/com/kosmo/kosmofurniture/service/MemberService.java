package com.kosmo.kosmofurniture.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kosmo.kosmofurniture.domain.Member;
import com.kosmo.kosmofurniture.domain.SearchDto;
import com.kosmo.kosmofurniture.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletRequest;
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
        memberMapper.save(member);
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

    public boolean checkAccount(String account) {
        Member member = memberMapper.findByAccount(account);
        return (member == null) ? false : true;
    }

    public Page<Member> getmembersWithSearchAndPagination(HttpServletRequest request) {
        PageHelper.startPage(request);
        SearchDto searchDto = new SearchDto();
        searchDto.setSearch(request.getParameter("search"));
        searchDto.setSection(request.getParameter("section"));
        return memberMapper.findWithSearchAndPagination(searchDto);
    }
}
