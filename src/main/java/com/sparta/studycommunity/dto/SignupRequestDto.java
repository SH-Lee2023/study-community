package com.sparta.studycommunity.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Size(min=4, max=10, message = "최소 4자 이상, 10자 이하만 가능합니다")
    @Pattern(regexp = "^[a-z0-9]*$", message = "알파벳 소문자, 숫자만 가능합니다.")
    private String username;
    @Size(min=8, max=15, message = "최소 8자 이상, 15자 이하만 가능합니다")
    @Pattern(regexp = "^[a-zA-Z0-9~`!@#$%^&*()-_=+\\\\[{\\\\]}\\\\\\\\|;:'\\\",<.>/?]*$", message = "알파벳, 숫자, 특수문자만 가능합니다.")
    private String password;
}