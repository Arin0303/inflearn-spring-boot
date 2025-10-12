// 게시글 작성 폼(DTO)
package com.example.__assignment.controller;

import com.example.__assignment.domain.PostStatus;

public class PostForm {
    private String title;
    private String content;
    private PostStatus postStatus;

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


    public PostStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }
}
