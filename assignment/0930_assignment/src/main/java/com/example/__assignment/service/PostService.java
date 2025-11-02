package com.example.__assignment.service;

import com.example.__assignment.controller.PostForm;
import com.example.__assignment.domain.Post;
import com.example.__assignment.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // 트랜잭션 관리(붙어있는 클래스 하위 메서드들은 모두 하나의 트랜잭션 안에서 처리됨)
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 글 생성
     */
    public Long createPost(Post post) {

        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }
    /**
     * 전체 게시글 목록 조회
     */

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Post updatePost(Long id, PostForm form) {
        // 요청 id 존재 여부 조회
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 엔티티의 필드를 form에서 받은 데이터로 변경
        if(form.getTitle() != null) {
            post.setTitle(form.getTitle());
        }
        if(form.getContent() != null) {
            post.setContent(form.getContent());
        }
        if(form.getPostStatus() != null) {
            post.setStatus(form.getPostStatus());
        }

        // repository.save(post)를 호출할 필요가 없다
        // -> 메소드가 @Transactional과 함께 종료되면 JPA가 변경사항을 DB에 UPDATE하기 때문

        return post; // 변경이 적용된 엔티티 반환
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postRepository.deleteById(id);
    }





}


