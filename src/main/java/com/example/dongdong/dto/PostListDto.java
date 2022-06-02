package com.example.dongdong.dto;

import com.example.dongdong.domain.Post;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostListDto {
    private Long id;
    private String title;
    private String content;
    private String createDate;
    private int viewCount;


    public PostListDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createDate = post.getContent();
        this.viewCount = post.getViewCount();
    }
}
