package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    // Main : Sub = Parent : Child
    // User : Post = 1 : *
    // 지연로딩: 사용자 엔티티 조회 시 매번 포스트 엔티티를 조회하는 것이 아닌,
    // 포스트 데이터가 로딩되는 시점에 필요한 사용자를 가져옴
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore // 해당 값은 전송되는 Json 내에서 제외됨
    private User user;
}
