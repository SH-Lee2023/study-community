package com.sparta.studycommunity.controller;

import com.sparta.studycommunity.dto.ProfileRequestDto;
import com.sparta.studycommunity.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    public final ProfileService profileService;

    public TestController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateProfile(@PathVariable Long id, @RequestBody ProfileRequestDto requestDto) {
        return profileService.updateProfile(id, requestDto);
    }
}
