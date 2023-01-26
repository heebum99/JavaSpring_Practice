package heeboo.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
        //return hello =>ViewResolver가 resources/templates/hello.html을 찾아 렌더링
    }

    //정적컨텐츠 => 클라이언트에게 파일을 그대로 제공
    //MVC, template 엔진 => template 엔진을 model, view, controller 방식으로 쪼개고
    //view를 html방식으로 렌더링해서 렌더링이 된 html을 클라이언트에게 제공
    //API => 객체를 반환하는 방식, httpMessageConverter를 통해서 json으로 바꿔서 반환, view 같은건 따로 x

    //MVC 방식
    //MVC: Model, View, Controller => 역할분담
    //View => 화면을 그리는것에만 집중
    //Controller => 비지니스로직, 내부 로직을 처리하는데만 집중
    @GetMapping("hello-mvc")
    //외부에서 Parameter를 받기 위해 @RequestParam 사용
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //html이 아닌 그냥 문자 그대로 출력하는 방식
    @GetMapping("hello-string")
    @ResponseBody //http의 Body부분에 아래 문자내용을 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    //API 방식
    //XML 구조 => <html>  ~  </html>
    //json 구조로 출력됨(key-value 형태)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) { //key = name, value = name에 넣은 값
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
