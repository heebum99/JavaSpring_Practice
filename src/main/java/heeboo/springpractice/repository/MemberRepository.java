package heeboo.springpractice.repository;

import heeboo.springpractice.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 저장

    //Optional => Null을 반환하는 방법 중 하나로 NullPointException이 발생하지 않도록 도와줌
    Optional<Member> findById(long id);

    Optional<Member> findByName(String name);

    List<Member> findAll(); //모든 회원리스트 리턴
}
