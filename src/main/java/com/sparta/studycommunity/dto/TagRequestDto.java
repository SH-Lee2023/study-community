package com.sparta.studycommunity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TagRequestDto {
    private List<String> tagNames;
}
