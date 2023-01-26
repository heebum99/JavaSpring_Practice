package heeboo.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//빌드, 실행하기
/*
1.cmd를 통해 현재 디렉토리에서 gradle.bat build => 빌드
2.cd build
3.cd libs
4.libs 디렉토리 안에 만들어져있는 jar파일 실행
=> java -jar 파일명 =>실행

안되는 경우 현재 디렉토리에서 clean build 먼저 실행후 1번부터 진행
*/
@Controller
public class HelloController {

    @GetMapping("hello") //웹 어플리케이션에서 /hello라고 들어오면 이 메소드를 호출해주는 역할
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
        //return hello =>ViewResolver가 resources/templates/hello.html을 찾아 렌더링
    }
}
