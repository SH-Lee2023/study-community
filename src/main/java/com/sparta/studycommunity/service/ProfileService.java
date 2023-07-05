package com.sparta.studycommunity.service;

import com.sparta.studycommunity.dto.CommonResponseDto;
import com.sparta.studycommunity.dto.ProfileRequestDto;
import com.sparta.studycommunity.entity.User;
import com.sparta.studycommunity.jwt.JwtUtil;
import com.sparta.studycommunity.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;

@Component
public class ProfileService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    public ProfileService(UserRepository userRepository, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }
    @Transactional
    public ResponseEntity updateProfile(Long id, ProfileRequestDto requestDto) {
//        String tokenValue = jwtUtil.getTokenFromRequest(req);
//        System.out.println(tokenValue);
//        Claims info = jwtUtil.getUserInfoFromToken(tokenValue);
//        info.setSubject(requestDto.getUsername());

//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(),null));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = findUser(id);
        user.update(requestDto);

        CommonResponseDto commonResponseDto = new CommonResponseDto();
        commonResponseDto.setMsg("회원 정보 수정");
        commonResponseDto.setStatusCode(200);
        return ResponseEntity.ok().body(commonResponseDto);
    }
    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("유저가 존재하지 않습니다."));
    }
}
