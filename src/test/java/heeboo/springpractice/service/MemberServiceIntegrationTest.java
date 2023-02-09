package heeboo.springpractice.service;

import heeboo.springpractice.domain.Member;
import heeboo.springpractice.repository.MemberRepository;
import heeboo.springpractice.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//Spring Test시 작성
@SpringBootTest
@Transactional //트랜잭션에 커밋을 해야 DB에 반영되는데 커밋되기 전에 롤백해 DB에 반영되지 않게 하는 역할
class MemberServiceIntegrationTest {

    //Test할때 필드 기반으로 Autowired 하는것이 편함
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

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

    }

}