package com.sparta.studycommunity.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CommonResponseDto {
    private String msg;
    private int statusCode;
}
