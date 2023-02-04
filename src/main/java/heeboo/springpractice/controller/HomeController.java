package heeboo.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //이 Controller가 없으면 index.html 화면이 메인화면
    @GetMapping("/") //localhost:8080에 들어오면 바로 호출되는 화면을 의미
    public String home(){
        return "home";
    }
}
