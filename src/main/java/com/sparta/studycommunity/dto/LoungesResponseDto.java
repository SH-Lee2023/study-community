package com.sparta.studycommunity.dto;

import com.sparta.studycommunity.entity.Lounges;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class LoungesResponseDto {
    private Long id;
    private String userId;
    private String contents;
    private Long likeCount;
    private Timestamp createAt;

    public LoungesResponseDto(Lounges lounges) {
        this.id = lounges.getId();
        this.userId = lounges.getUserId();
        this.contents = lounges.getContents();
        this.likeCount = lounges.getLikeCount();
        this.createAt = lounges.getCreateAt();
    }
}
