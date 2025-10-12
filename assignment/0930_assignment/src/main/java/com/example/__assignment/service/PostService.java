package com.example.__assignment.service;

import com.example.__assignment.domain.Post;
import com.example.__assignment.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional // 트랜잭션 관리(붙어있는 클래스 하위 메서드들은 모두 하나의 트랜잭션 안에서 처리됨)
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 글 생성
     */
    public Post createPost(Post post) {
        return postRepository.save(post);
    }
    /**
     * 전체 게시글 목록 조회
     */

    public List<Post> findPosts() {
        return postRepository.findAll();
    }
    /**
     * 게시글 단일 조회
     */
    @Transactional(readOnly = true)
    public Optional<Post> findOne(Long postId) {
        return postRepository.findById(postId);
    }
}
