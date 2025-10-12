package com.example.__assignment.domain;

// enum은 JPA 엔티티가 아니므로 @Entity 어노테이션은 필요 없음

public enum PostStatus {

    PUBLIC("전체 공개"),
    PRIVATE("비공개");

    // 1. 화면에 표시할 한글 이름을 저장할 final 필드를 선언.
    private final String displayName;

    // 2. 필드를 초기화하는 생성자를 만든다.
    PostStatus(String displayName) {
        this.displayName = displayName;
    }

    // 3. Thymeleaf 등에서 값을 가져갈 수 있도록 getter를 만든다.
    public String getDisplayName() {
        return displayName;
    }
}