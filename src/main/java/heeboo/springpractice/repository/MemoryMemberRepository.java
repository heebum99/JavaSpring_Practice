package heeboo.springpractice.repository;

import heeboo.springpractice.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //0,1,2.... 키 값을 생성해주는 역할

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //ID는 System 자동 세팅
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        //Null값이 들어갈 가능성이 있는 경우 Optional로 감싸기
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream() //stream 객체 생성
                .filter(member -> member.getName().equals(name)) //filter로 원하는 데이터 가공,추출
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
