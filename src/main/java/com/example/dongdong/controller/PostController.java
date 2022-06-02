package com.example.dongdong.controller;


import com.example.dongdong.dto.PostDto;
import com.example.dongdong.dto.PostListDto;
import com.example.dongdong.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor

public class PostController {

    private final PostService postService;

    //등록
    @PostMapping("/post")
    public void post(@RequestBody PostDto postDto) {
        postService.post(postDto);
    }

    //전체 조회
    @GetMapping("/postList")
    public List<PostListDto> postList(@RequestParam("orderby") String orderBy) {
        return postService.postList(orderBy);
    }


    //수정
    @PutMapping("/post/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        postService.updatePost(id, postDto);
    }

    //삭제
    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    //상세 글
    @GetMapping("/post/{id}")
    public PostListDto selectedPost(@PathVariable Long id, @RequestParam String password) {
        return postService.selectedPost(id,password);
    }
    //검색
    @GetMapping("/post")
    public List<PostListDto> searchPost(@RequestParam String content ){
       return postService.searchPost(content);
    }
    //조회수 증가
    @PutMapping("/viewCount")
    public void plusViewCount(@RequestParam Long id){
        postService.plusViewCount(id);
    }

}
