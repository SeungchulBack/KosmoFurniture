package com.kosmo.kosmofurniture;

import com.kosmo.kosmofurniture.domain.Member;
import com.kosmo.kosmofurniture.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Configuration
public class CommonBeans implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    ApplicationContext appCxt;

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.kosmo.kosmofurniture.domain");
        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public CommandLineRunner initDB(SqlSessionTemplate sqlSessionTemplate) {

        return (args) -> {

            MemberMapper memberMapper = sqlSessionTemplate.getMapper(MemberMapper.class);

            // 일반사용자 데이터
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

                memberMapper.save(member);
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
        };
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {

        SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate)appCxt.getBean("sqlSessionTemplate");
        MemberMapper memberMapper = sqlSessionTemplate.getMapper(MemberMapper.class);

        memberMapper.deleteAll();
        memberMapper.setAutoIncToZero();
    }
}
