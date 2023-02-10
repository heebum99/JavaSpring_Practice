package heeboo.springpractice.repository;

import heeboo.springpractice.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; //EntityManager를 통해 Jpa 모든것이 동작

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist() => 영구저장
        return member;
    }

    //pk 기반은 jpa로 가능, 나머지는 jpql로 작성해줘야함
    @Override
    public Optional<Member> findById(long id) { 
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class) //jpql
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
        //Member Entity를 대상으로 쿼리를 날림
    }
}
