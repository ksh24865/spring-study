package com.example.springbootcommunityweb.domain;

import com.example.springbootcommunityweb.domain.enums.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Board {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키가 자동으로 할당되도록 설정 ,IDENTITY = 키생성을 DB에 위임
    private Long idx;

    @Column
    private String title;

    @Column
    private String subTitle;
    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING) // Enum타입 매핑, DB에 String으로 변환하여 저장
    private BoardType boardType;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @OneToOne(fetch = FetchType.LAZY)
    // board와 User간 1:1관계 형성,
    // LAZY = User객체를 조회하는 시점이 아닌 객체가 실제로 사용될 때 조회
    // DB에 저장될 때는 User객체가 아닌 user.idx가 저장
    private User user;

    @Builder
    public Board(String title, String subTitle, String content, BoardType boardType,
                 LocalDateTime createdDate, LocalDateTime updatedDate, User user) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.boardType = boardType;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.user = user;
    }


}
