package com.sparta.studycommunity.dto;

import com.sparta.studycommunity.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String image;
    private Integer scrapCount;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<TagResponseDto> postTagList = new ArrayList<>();
    private List<CommentResponseDto> commentList = new ArrayList<>();

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.image = post.getImage();
        this.scrapCount = post.getScrapCount();
        this.username = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.postTagList = post.getPostTagList()
                            .stream()
                            .map(postTag -> new TagResponseDto(postTag.getTag()))
                            .toList();
        this.commentList = post.getCommentList()
                            .stream()
                            .map(CommentResponseDto::new)
                            .sorted(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed())
                            .toList();
    }
}
