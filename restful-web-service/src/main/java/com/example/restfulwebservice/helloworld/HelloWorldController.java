package com.example.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource; // 현재 스프링에 등록된 빈들 중에 같은 타입을 가진 빈을 자동으로 주입.

    // @RequestMapping(method=RequestMethod.GET, path="/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World"); // 클래스 반환시 json으로 자동 변경
    }
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name) );
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("greeting.message",null,locale);

    }
}
