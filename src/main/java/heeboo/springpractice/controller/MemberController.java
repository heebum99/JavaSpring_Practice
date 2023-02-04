package heeboo.springpractice.controller;

import heeboo.springpractice.domain.Member;
import heeboo.springpractice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//Controller => 외부 요청을 받는다
//Service => 비지니스 로직 생성
//Repository => 데이터를 저장
@Controller //컴포넌트 스캔 방식
public class MemberController { //스프링 컨테이너가 뜰 때 Controller 생성

    private final MemberService memberService;

    @Autowired  //Autowired => Controller - Service - Repository를 연결하는 역할
    //스프링 컨테이너에 올라가있는 애들만 Autowired가 실행됨
    //Dependency Injection(DI) => 생성자에서 Autowired를 통해 MemberController가 생성될때
    //spring bean에 등록되어 있는 memberService 객체를 가져다가 넣어줌
    //생성자를 통한 DI => 생성자 주입
    //생성자 주입 외에도 필드주입(잘 사용x), setter주입(누군가 사용할때 public으로 되어있어야함), 생성자 주입이 가장 좋음
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") //URL창에서 입력후 Enter치는 경우(조회하는 경우 사용)
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //데이터를 form에 넣어서 전달할 때 사용
    //해당 URL에서 Post 방식으로 넘어오면 아래 메소드가 호출됨
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/"; //redirect로 홈 화면으로 이동
    }
}
