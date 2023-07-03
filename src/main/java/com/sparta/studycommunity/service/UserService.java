package com.sparta.studycommunity.service;

import com.sparta.studycommunity.dto.*;
import com.sparta.studycommunity.entity.User;
import com.sparta.studycommunity.entity.UserRoleEnum;
import com.sparta.studycommunity.jwt.JwtUtil;
import com.sparta.studycommunity.repository.UserRepository;
import com.sparta.studycommunity.dto.LoginRequestDto;
import com.sparta.studycommunity.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ADMIN_TOKEN, 관리자 토큰을 넣었는지 확인을 해서 제대로 넣었으면 관리자로서 회원가입 할 수 있도록
    private final String ADMIN_TOKEN = "1111";

    public ResponseEntity signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String passwordDecoded = requestDto.getPassword();
        String password = passwordEncoder.encode(requestDto.getPassword()); // 패스워드 평문 암호화

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
            exceptionResponseDto.setMsg("중복된 username 입니다.");
            exceptionResponseDto.setStatusCode(400);
            return ResponseEntity.badRequest().body(exceptionResponseDto);
        }

        // 관리자 권한 사용x, 사용자 ROLE USER로 임의로 지정
        UserRoleEnum role = UserRoleEnum.USER;

        // 사용자 등록
        User user = new User(username, passwordDecoded, password, role);
        userRepository.save(user);

        // response, 상황에 따라 임의로 더 정확한 상태 코드 반환
        SignupResponseDto signupResponseDto = new SignupResponseDto();
        signupResponseDto.setMsg("회원가입 성공");
        signupResponseDto.setStatusCode(200);
        return ResponseEntity.ok().body(signupResponseDto);
    }

//    public ResponseEntity login(LoginRequestDto requestDto, HttpServletResponse res) {
//        String username = requestDto.getUsername();
//        String password = requestDto.getPassword();
//
//        // username 확인
//        Optional<User> checkUsername = userRepository.findByUsername(username);
//        if (checkUsername.isEmpty()) {
//            ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
//            exceptionResponseDto.setMsg("회원을 찾을 수 없습니다.");
//            exceptionResponseDto.setStatusCode(400);
//            return ResponseEntity.badRequest().body(exceptionResponseDto);
//        }
//
//        // User 객체 받아오기
//        User user = userRepository.findByUsername(username).get();
//
//        // password 확인
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
//            exceptionResponseDto.setMsg("회원을 찾을 수 없습니다.");
//            exceptionResponseDto.setStatusCode(400);
//            return ResponseEntity.badRequest().body(exceptionResponseDto);
//        }
//
//        // JWT 생성, header에 저장, 쿠키에 저장 후 Response 객체에 추가
//        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(jwtUtil.getAuthorizationHeader(), token);
//        jwtUtil.addJwtToCookie(token, res);
//
//        // 클라이언트에 response 반환
//        LoginResponseDto loginResponseDto = new LoginResponseDto();
//        loginResponseDto.setMsg("로그인 성공");
//        loginResponseDto.setStatusCode(200);
//
//        return ResponseEntity.ok().headers(headers).body(loginResponseDto);
//    }
}