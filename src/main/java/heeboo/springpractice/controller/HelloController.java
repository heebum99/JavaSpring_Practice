package heeboo.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") //웹 어플리케이션에서 /hello라고 들어오면 이 메소드를 호출해주는 역할
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
        //return hello =>ViewResolver가 resources/templates/hello.html을 찾아 렌더링
    }
}
