package com.kosmo.kosmofurniture.mapper;

import com.github.pagehelper.Page;
import com.kosmo.kosmofurniture.domain.Member;
import com.kosmo.kosmofurniture.domain.Product;
import com.kosmo.kosmofurniture.domain.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(Member member);
    List<Member> findAll();
    Page<Member> findWithSearchAndPagination(SearchDto searchDto);
    Member findById(Long memberId);
    Member findByAccount(String account);
    Member findBySsn(String ssn);
    void deleteAll();
    void setAutoIncToZero();
}
