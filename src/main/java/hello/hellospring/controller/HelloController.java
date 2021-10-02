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
        model.addAttribute("data", "hello!!");
        // viewResolver
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName("name");
        // 객체 리턴 {"name": "Spring"}
        // HttpMessageConverter -> StringConverter(문자)/JsonConverter(객체)
        // 기본 객체 처리: MappingJackson2HttpMessageConverter / 기본 문자 처리: StringHttpMessageConverter
        return hello;
    }

    // 객체 만들기
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
