package com.example.dongdong.service;

import com.example.dongdong.domain.Post;
import com.example.dongdong.dto.PostDto;
import com.example.dongdong.dto.PostListDto;
import com.example.dongdong.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void post(PostDto postDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setPassword(postDto.getPassword());
        post.setTitle(postDto.getTitle());
        post.setTime(LocalDateTime.now());
        postRepository.save(post);
    }

    public List<PostListDto> postList(String orderBy) {
        List<Post> posts = new ArrayList<>();
        if (orderBy.equals("time")) {
            posts = postRepository.findAllByOrderByTimeDesc();
        } else {
            posts = postRepository.findAllByOrderByViewCount();
        }


        List<PostListDto> postListDtos = new ArrayList<>();
        for (Post post : posts) {
            PostListDto postDto = new PostListDto(post);
            postListDtos.add(postDto);
        }
        return postListDtos;
    }

    public void updatePost(Long id, PostDto postDto) {
        Optional<Post> post = postRepository.findById(id);
        post.get().setTitle(postDto.getTitle());
        post.get().setContent(postDto.getContent());
    }

    public void deletePost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        postRepository.delete(post.get());
    }

    public PostListDto selectedPost(Long id, String password) {
        Optional<Post> post = postRepository.findById(id);
        if (password.equals(post.get().getPassword())) {
            PostListDto postListDto = new PostListDto(post.get());
            return postListDto;

        } else {
            return null;
        }


    }

    public List<PostListDto> searchPost(String content) {
        List<Post> post = postRepository.findByTitleContains(content);
        List<PostListDto> postListDtos = new ArrayList<>();

        for (Post post1 : post) {
            PostListDto postListDto = new PostListDto(post1);
            postListDtos.add(postListDto);
        }
        return postListDtos;
    }

    public void plusViewCount(Long id) {
        Optional<Post> post = postRepository.findById(id);
        post.get().setViewCount(post.get().getViewCount() + 1);
    }
}
