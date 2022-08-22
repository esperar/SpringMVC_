package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
