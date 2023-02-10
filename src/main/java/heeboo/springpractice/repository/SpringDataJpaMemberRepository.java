package heeboo.springpractice.repository;

import heeboo.springpractice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//스프링 데이터 JPA
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    
    @Override
    Optional<Member> findByName(String name);
    //스프링 데이터 JPA가 규칙(메서드 이름, 파라미터 등등)에 따라 JPQL 을 생성 => select m from Member m where m.name = ?
}
