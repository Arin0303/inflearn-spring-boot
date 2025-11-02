package com.example.__assignment.service;

import com.example.__assignment.controller.PostForm;
import com.example.__assignment.domain.Post;
import com.example.__assignment.domain.PostStatus;
import com.example.__assignment.repository.PostRepository;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@SpringBootTest // 스프링 컨테이너와 모든 빈 로드
@Transactional // 각 @Test가 끝날 때마다 DB 롤백
public class PostServiceIntegrationTest {

    // 의존성 주입
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("게시글 작성 테스트")
    void createPost() {
        // given
        Post post = new Post();
        post.setTitle("테스트 제목");
        post.setContent("테스트 내용");
        post.setStatus(PostStatus.PRIVATE);

        // when
        long savedId = postService.createPost(post);
        // then
        Post findPost = postRepository.findById(savedId).get();

        assertThat(findPost.getId()).isEqualTo(savedId);
        assertThat(findPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(findPost.getContent()).isEqualTo(post.getContent());
    }

    @Test
    @DisplayName("게시글 전체 조회 테스트")
    void findAllPosts() {

        // given
        Post post1 = new Post();
        Post post2 = new Post();

        post1.setTitle("테스트 제목1");
        post1.setContent("테스트 내용1");
        post1.setStatus(PostStatus.PRIVATE);

        post2.setTitle("테스트 제목2");
        post2.setContent("테스트 내용2");
        post2.setStatus(PostStatus.PUBLIC);

        postRepository.save(post1);
        postRepository.save(post2);


        // when
        List<Post> findPosts = postService.findPosts();

        //then
        assertThat(findPosts.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void updatePost() {
        Post originalPost = new Post();

        // given
        originalPost.setTitle("원본 제목");
        originalPost.setContent("원본 내용");
        originalPost.setStatus(PostStatus.PRIVATE);

        postRepository.save(originalPost);
        Long postId = originalPost.getId(); // 저장된 ID 확보

        PostForm updatedForm = new PostForm();
        updatedForm.setTitle("수정된 제목");
        updatedForm.setContent("수정된 내용");
        updatedForm.setPostStatus(PostStatus.PUBLIC);


        // when
        postService.updatePost(postId, updatedForm); // 수정된 폼 제출

        // then
        // db에서 해당 id의 게시글 다시 조회
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("테스트 오류: DB에서 post를 찾을 수 없음"));

        assertThat(findPost.getTitle()).isEqualTo("수정된 제목");
        assertThat(findPost.getContent()).isEqualTo("수정된 내용");
        assertThat(findPost.getStatus()).isEqualTo(PostStatus.PUBLIC);

    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void deletePost() {
        // given
        Post post = new Post();
        post.setTitle("삭제될 제목");
        post.setContent("삭제될 내용");
        post.setStatus(PostStatus.PRIVATE);


        postRepository.save(post);
        Long postId = post.getId();

        // when
        postService.deletePost(postId);

        //then
        Optional<Post> result = postRepository.findById(postId);

        assertThat(result).isEmpty();


    }

}
