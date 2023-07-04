package com.sparta.studycommunity.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter // getter 처리 안하면 임의로 사용한 필드값 불러올 수 없음, bad request에 대한 실제 에러 나감
@Setter
@Component
public class ExceptionResponseDto {
    private String msg;
    private int statusCode;
}
