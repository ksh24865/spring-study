package com.example.restfulwebservice.helloworld;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //setter getter to_string 메소드 자동 생성
@AllArgsConstructor // 멤버를 갖고 생성자 자동 생성
@NoArgsConstructor //
public class HelloWorldBean {
    private String message;

//    public HelloWorldBean(String message) {
//        this.message = message;
//    }
}
