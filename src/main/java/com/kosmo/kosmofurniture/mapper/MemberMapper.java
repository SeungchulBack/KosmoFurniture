package com.kosmo.kosmofurniture.mapper;

import com.kosmo.kosmofurniture.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(Member member);
    List<Member> findAll();
    Member findById(Long memberId);
    Member findByAccount(String account);
    Member findBySsn(String ssn);
    void deleteAll();
    void setAutoIncToZero();
}
