package heeboo.springpractice.service;

import heeboo.springpractice.domain.Member;
import heeboo.springpractice.repository.MemberRepository;
import heeboo.springpractice.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service //컴포넌트 스캔 방식
public class MemberService { //ctrl shift t => 테스트 클래스 생성
    private final MemberRepository memberRepository;

    @Autowired
    //MemberService를 생성할때 spring bean에 등록되어 있는 memberRepository 객체를 가져다가 넣어줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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
