package com.kosmo.kosmofurniture.mapper;

import com.kosmo.kosmofurniture.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    Long save(Member member);
    Member findById(Long memberId);
    void deleteAll();
    void setAutoIncToZero();
}
