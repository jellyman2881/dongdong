package com.example.dongdong.repository;

import com.example.dongdong.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
 List<Post> findByTitleContains(String content);
 List<Post> findAllByOrderByTimeDesc();
 List<Post> findAllByOrderByViewCount();
}
