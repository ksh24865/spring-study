package com.example.springbootcommunityweb.domain.enums;

public enum BoardType {

//    noitce("공지사항"),
    free("자유게시판");

    private String value;

    BoardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }




}
