// 사용자의 요청/응답을 처리 (API 엔드포인트)

package com.example.__assignment.controller;

import com.example.__assignment.domain.Post;
import com.example.__assignment.domain.PostStatus;
import com.example.__assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired // 의존성 주입
    public PostController(PostService postService) {
        this.postService = postService;
    }


    // 게시글 작성
    @PostMapping("/posts/new")
    public Long create(@RequestBody PostForm form){ // 폼에서 넘어온 데이터가 form 객체에 담김
        Post post = new Post(); // post 엔티티 객체 생성

        post.setTitle(form.getTitle()); // form 객체에서 제목을 꺼내 Post 객체에 저장
        post.setContent(form.getContent());
        post.setStatus(form.getPostStatus());

        return postService.createPost(post); // 게시글 id값 반환
    }

    // 게시글 전체 조회
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        List<Post> posts = postService.findPosts();
        return posts;
    }

    // 게시글 단일 조회


    // 게시글 수정
    /**
     * 게시글 수정 API
     * @param id 수정할 게시글의 ID (URL 경로에서 가져옴)
     * @param form 수정할 내용 (JSON 본문에서 가져옴)
     */
    @PatchMapping("/posts/{id}") // HTTP PATCH /posts/10
    public Post updatePost(
            @PathVariable Long id,
            @RequestBody PostForm form
    ) {
        Post updatedPost = postService.updatePost(id, form);

        return updatedPost;
    }
    // 게시글 삭제
    /**
     * @param id 삭제할 게시글의 ID
     */

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);

        return ResponseEntity.noContent().build();
    }
}
