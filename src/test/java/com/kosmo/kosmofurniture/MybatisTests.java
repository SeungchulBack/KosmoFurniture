package com.kosmo.kosmofurniture;

import com.kosmo.kosmofurniture.domain.Member;
import com.kosmo.kosmofurniture.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class) //Spring으로 Junit 테스트를 진행하기 위해 있어야 함
@SpringBootTest
@Slf4j
public class MybatisTests {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Test
    public void connection_test() {
        try(Connection con = sqlSession.getConnection()){
        }catch(Exception e){
            Assert.fail("DB커넥션 에러");
        }
    }

    @Test
    @Transactional
    public void sessionTemplateTest() {

        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        MemberMapper memberMapper = sqlSessionTemplate.getMapper(MemberMapper.class);

        for (int i = 1; i < 41; i++) {
            Member member = new Member();
            member.setAccount("kim" + i);
            member.setPwd("1234");
            member.setFullName("김사람" + i);
            member.setEmail("cl" + i + "@tt.com");
            member.setAddress("서울 금천구 시흥대로" + i);
            member.setPhone("01033334443");
            member.setRole("ROLE_USER");
            member.setSsn("930202-1112211");
            member.setCreatedAt(LocalDateTime.now());

            member = memberMapper.save(member);
            log.debug(member.getMemberId().toString());
            log.debug(member.getFullName());
        }

        //관리자 데이터
        Member member = new Member();
        member.setAccount("lee");
        member.setPwd("1234");
        member.setFullName("이사람");
        member.setEmail("cl@tt.com");
        member.setAddress("서울 금천구 시흥대로");
        member.setPhone("01033334444");
        member.setRole("ROLE_ADMIN");
        member.setSsn("930202-1112211");
        member.setCreatedAt(LocalDateTime.now());

        memberMapper.save(member);

    }
}
