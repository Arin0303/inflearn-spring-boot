package com.example.hello_spring.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY: db가 자동으로 id 생성
    private Long id; // 시스템이 자동으로 저장하는 값

    @Column(name = "username")
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

