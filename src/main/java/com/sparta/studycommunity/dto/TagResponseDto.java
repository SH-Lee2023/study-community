package com.sparta.studycommunity.dto;

import com.sparta.studycommunity.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TagResponseDto {
    private Long id;
    private String name;

    public TagResponseDto(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }
}
