package heeboo.springpractice.service;

import heeboo.springpractice.domain.Member;
import heeboo.springpractice.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    //회원 가입
    public long join(Member member) {
        //같은 이름이 있는 중복 회원x
        //ctrl atl v
        //ctrl alt shift t => 메서드 추출
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //ifPresent() 메서드 => null이 아닌 어떤 값이 있으면 logic이 동작
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(long memberId) {
        return memberRepository.findById(memberId);
    }
}
