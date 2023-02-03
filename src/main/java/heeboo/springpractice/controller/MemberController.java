package heeboo.springpractice.controller;

import heeboo.springpractice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//Controller => 외부 요청을 받는다
//Service => 비지니스 로직 생성
//Repository => 데이터를 저장
@Controller //컴포넌트 스캔 방식
public class MemberController { //스프링 컨테이너가 뜰 때 Controller 생성

    private final MemberService memberService;

    @Autowired  //Autowired => Controller - Service - Repository를 연결하는 역할
    //Dependency Injection(DI) => 생성자에서 Autowired를 통해 MemberController가 생성될때
    //spring bean에 등록되어 있는 memberService 객체를 가져다가 넣어줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
