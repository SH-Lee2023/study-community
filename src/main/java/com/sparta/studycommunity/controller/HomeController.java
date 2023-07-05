package com.sparta.studycommunity.controller;

import com.sparta.studycommunity.dto.ProfileRequestDto;
import com.sparta.studycommunity.security.UserDetailsImpl;
import com.sparta.studycommunity.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {

    public final ProfileService profileService;

    public HomeController(ProfileService profileService) {
        this.profileService = profileService;
    }
    // 커뮤니티 메인 페이지로 이동
    @GetMapping("/home")
    public String mainPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        return "home";
    }

    @GetMapping("/profiles")
    public String profilePage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("id", userDetails.getUser().getId());
        return "profile";
    }

//    @PutMapping("/users/{id}")
//    public ResponseEntity updateProfile(@PathVariable Long id, @RequestBody ProfileRequestDto requestDto) {
//        return profileService.updateProfile(id, requestDto);
//    }
}
