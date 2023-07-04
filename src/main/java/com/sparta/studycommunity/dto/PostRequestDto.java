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
    private String image;
    private String tag;

    public PostRequestDto(String title, String contents, String image, String tag) {
        this.title = title;
        this.contents = contents;
        this.image = image;
        this.tag = tag;
    }
}
