package com.kosmo.kosmofurniture.service;

import com.kosmo.kosmofurniture.domain.Member;
import com.kosmo.kosmofurniture.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public List<Member> getAllMembers() {
        return memberMapper.findAll();
    }
    public Member getSingleMember(Long memberId) {
        return memberMapper.findById(memberId);
    }

    public Member getMemberBySsn(String ssn) {
        return memberMapper.findBySsn(ssn);
    }
}
