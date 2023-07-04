package com.sparta.studycommunity.dto;

import com.sparta.studycommunity.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String imageUrl;
    private String tag;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.imageUrl = post.getImageUrl();
        this.tag = post.getTag();
    }
}
