// 사용자의 요청/응답을 처리 (API 엔드포인트)

package com.example.__assignment.controller;

import com.example.__assignment.domain.Post;
import com.example.__assignment.domain.PostStatus;
import com.example.__assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired // 의존성 주입
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 사용자에게 게시글 작성 폼을 보여줌
    @GetMapping("/posts/new")
    public String GetForm(Model model) {
        model.addAttribute("postStatuses", PostStatus.values()); // PostStatus를 배열로 만들어서 추가
        return "posts/createForm"; // 이 이름의 HTML 파일을 찾아서 보여줌(templates/posts/createPostForm.html)-렌더링해서 view 보여주기
    }

    // 게시글 작성
    @PostMapping("/posts/new")
    public String create(PostForm form){ // 폼에서 넘어온 데이터가 form 객체에 담김
        Post post = new Post(); // post 엔티티 객체 생성

        post.setTitle(form.getTitle()); // form 객체에서 제목을 꺼내 Post 객체에 저장
        post.setContent(form.getContent());
        post.setStatus(form.getPostStatus());

        postService.createPost(post);

        return "redirect:/posts"; // 게시판으로 리다이렉트(재요청 HTTP 302)
    }

    // 게시글 목록 페이지 보여주기
    @GetMapping("/posts")
    public String listPosts(Model model) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts); // Post객체를 모델에 추가
        model.addAttribute("postStatuses", PostStatus.values()); // postStatus의 enum값들을 모델에 추가
        return "posts/list"; // templates/posts/list.html
    }
}
