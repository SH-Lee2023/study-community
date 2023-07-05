package com.sparta.studycommunity.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.studycommunity.dto.LoginRequestDto;
import com.sparta.studycommunity.entity.UserRoleEnum;
import com.sparta.studycommunity.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

// 인증 필터
// UsernamePasswordAuthenticationFilter을 그대로 사용하면 세션 방식이라 jwt token을 사용하기 위해 커스텀하여 사용
@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도");
        try { // request.getInputStream() => HttpServletRequest의 body 데이터를 받아옴
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
//            System.out.println(requestDto.getUsername());
//            System.out.println(requestDto.getPassword());
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // attemptAuthentication 메서드 성공 시 Authentication 객체를 가지고 올 수 있음
        log.info("로그인 성공 및 JWT 생성");
        // Long id = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getId();
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();

        // String token = jwtUtil.createToken(id, role);
        String token = jwtUtil.createToken(username, role);
        jwtUtil.addJwtToCookie(token, response);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패");
        response.setStatus(401);
    }
}