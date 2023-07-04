package com.sparta.studycommunity.dto;

import com.sparta.studycommunity.entity.Lounges;
import lombok.Getter;
import org.w3c.dom.Text;

import java.sql.Timestamp;

@Getter
public class LoungesResponseDto {
    private Long id;
    private String user_id;
    private String contents;
    private Long like_Count;
    private Timestamp create_at;

    public LoungesResponseDto(Lounges lounges) {
        this.id = lounges.getId();
        this.user_id = lounges.getUser_id();
        this.contents = lounges.getContents();
        this.like_Count = lounges.getLike_Count();
        this.create_at = lounges.getCreate_at();
    }
}
