package com.example.hello_spring.domain;

public class Member {

    private Long id; // 시스템이 자동으로 저장하는 값
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}

