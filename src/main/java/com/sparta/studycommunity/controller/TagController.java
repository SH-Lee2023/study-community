package com.sparta.studycommunity.controller;

import com.sparta.studycommunity.dto.TagRequestDto;
import com.sparta.studycommunity.dto.TagResponseDto;
import com.sparta.studycommunity.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/tags")
    public void addTags(@RequestBody TagRequestDto requestDto) {
        List<String> tagNames = requestDto.getTagNames();
        tagService.addTags(tagNames);
    }

    @GetMapping("/tags")
    public List<TagResponseDto> getAllTags() {
        return tagService.getAllTags();
    }
}
