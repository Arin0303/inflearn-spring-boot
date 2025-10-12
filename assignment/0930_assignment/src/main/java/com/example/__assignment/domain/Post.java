// 데이터베이스 테이블과 매핑되는 객체(Entity)
package com.example.__assignment.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "post") // post 테이블과 매핑

public class Post {
    //======id 자동 생성=====
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =====칼럼 생성=====
    @Column(name = "title", length = 100, nullable = false)
    private String title; // title VARCHAR(100) NOT NULL

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content; // content TEXT NOT NULL

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable =false)
    private PostStatus status; // status post_status NOT NULL
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP


    // ===========getter, setter===========
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
