package heeboo.springpractice;

import heeboo.springpractice.repository.MemberRepository;
import heeboo.springpractice.repository.MemoryMemberRepository;
import heeboo.springpractice.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //자바 코드로 직접 스프링 빈 등록
public class SpringConfig {
    //MemberService와 MemberRepository를 스프링 빈에 등록
    //Controller는 Autowired를 이용해 컴포넌트 스캔방식을 이용해야함.
    @Bean
    public MemberService memberService() { //스프링 빈에 등록된 MemberRepository를 MemberService에 넣어줌
        return new MemberService(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
