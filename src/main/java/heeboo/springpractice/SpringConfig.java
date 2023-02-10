package heeboo.springpractice;

import heeboo.springpractice.repository.*;
import heeboo.springpractice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.JdbcAccessor;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration //자바 코드로 직접 스프링 빈 등록
public class SpringConfig {
    //MemberService와 MemberRepository를 스프링 빈에 등록
    //Controller는 Autowired를 이용해 컴포넌트 스캔방식을 이용해야함.

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;

//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() { //스프링 빈에 등록된 MemberRepository를 MemberService에 넣어줌
        //return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

}
