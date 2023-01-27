package heeboo.springpractice.repository;

import heeboo.springpractice.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트 케이스를 먼저 만들고 구현 클래스를 만드는 경우를 테스트 주도 개발이라고 한다.

    //이대로 테스트 진행 시 각각 진행하는 경우 문제가 없지만 한꺼번에 모든 메소드를 테스트 하는 경우
    //메소드 실행 순서에 따라 오류가 발생할 수 있다. => 테스트 하나 끝나면 데이터를 clear 해줘야함.
    @AfterEach //메소드 동작이 끝날때마다 호출시켜줌
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        System.out.println("result = " + (result == member));
        Assertions.assertEquals(result, member); //junit의 Assertions => 에러 검사를 하는 기능을 가진 boolean 식
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);//assertj의 Assertions
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
