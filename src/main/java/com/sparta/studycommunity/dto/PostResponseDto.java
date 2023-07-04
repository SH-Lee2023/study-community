package com.sparta.studycommunity.dto;

import com.sparta.studycommunity.entity.Post;
import com.sparta.studycommunity.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String image;
    private Integer scrapCount;
    private User user;
    private List<TagResponseDto> postTagList = new ArrayList<>();
    private List<CommentResponseDto> commentList = new ArrayList<>();

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.image = post.getImage();
        this.scrapCount = post.getScrapCount();
        this.user = post.getUser();
        this.postTagList = post.getPostTagList()
                            .stream()
                            .map(postTag -> new TagResponseDto(postTag.getTag()))
                            .toList();
//      this.commentList = post.getCommentList();
    }
}
