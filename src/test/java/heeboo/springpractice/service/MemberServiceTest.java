package heeboo.springpractice.service;

import heeboo.springpractice.domain.Member;
import heeboo.springpractice.repository.MemoryMemberRepository;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach //Test 실행시 마다 각각 실행됨
    public void beforeEach(){ //Test케이스와 원본 클래스에서 동일한 객체를 사용하기 위해서 => DI
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach //메소드 동작이 끝날때마다 호출시켜줌
    public void afterEach() {
        memberRepository.clearStore();
    }

    //test에서는 한글로 적어도 무관
    @Test
    void 회원가입() { //test시 진행 패턴 given -> when -> then
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //예외처리 Test도 중요!
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //assertThrows()의 의미: () -> memberService.join(member2) logic을 실행할때 IllegalStateException 이 예외가 터져야한다.
        //assertThrows(IllegalStateException.class,() -> memberService.join(member2));

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


//        try {
//            memberService.join(member2); //member1과 같은 이름이므로 중복체크에서 예외 발생
//            fail("예외가 발생해야 합니다.");
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}