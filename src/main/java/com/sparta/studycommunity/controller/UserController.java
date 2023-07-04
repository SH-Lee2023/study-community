package com.sparta.studycommunity.controller;

import com.sparta.studycommunity.dto.SignupRequestDto;
import com.sparta.studycommunity.security.UserDetailsImpl;
import com.sparta.studycommunity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 커뮤니티 메인 페이지로 이동
    @GetMapping("/home")
    public String mainPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        return "community";
    }
    
    // 회원가입 페이지로 이동
    @GetMapping("/users/signup")
    public String signupPage() {
        return "signup";
    }

    // 회원가입
    @PostMapping("/users/signup")
    @ResponseBody
    public ResponseEntity signUp(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        // 회원가입 Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            throw new IllegalArgumentException("회원가입 아이디 비밀번호 입력 오류");
        }
        return userService.signup(requestDto);
    }


}
