package com.sparta.studycommunity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String contents;
    private String imageUrl;
    private String tag;

    public PostRequestDto(String title, String contents, String imageUrl, String tag) {
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.tag = tag;
    }
}
