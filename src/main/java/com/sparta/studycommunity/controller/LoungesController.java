package com.sparta.studycommunity.controller;

import com.sparta.studycommunity.dto.LoungesRequestDto;
import com.sparta.studycommunity.dto.LoungesResponseDto;
import com.sparta.studycommunity.entity.Lounges;
import com.sparta.studycommunity.service.LoungesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController

public class LoungesController {

    private final LoungesService loungesService;

    @Autowired
    public LoungesController(LoungesService loungesService) {

        this.loungesService = loungesService;
    }

    @PostMapping("/lounges")
    public LoungesResponseDto createLounge(@RequestBody LoungesRequestDto requestDto) {
        return loungesService.createLounge(requestDto);

    }

    @GetMapping("lounges")
    public List<LoungesResponseDto> getLounges() {
        return loungesService.getLounges();

    }

    @PostMapping("/lounges/{id}/like")
    public void likeLounge(@PathVariable("id") Long id) {
        loungesService.likeLounge(id);
    }
}
