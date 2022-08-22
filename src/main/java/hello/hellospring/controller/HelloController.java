package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","spring");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name , Model model){
        // Model 안에 데이터를 key, value 값으로 담고, return 타입을 String 값으로 뷰의 이름을 지정해주면 뷰로 데이터가 전송되게 됩니다.
        model.addAttribute("name" , name);
        return "hello-template";
    }

    // @ResponseBody 를 사용하면 뷰 리졸버( viewResolver )를 사용하지 않음
    // 대신에 HTTP 의 BODY 에 문자 내용을 직접 반환
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // @ResponseBody 를 사용하고, 객체를 반환하면 객체가 JSON 으로 변환됨
    }

    static class Hello{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
