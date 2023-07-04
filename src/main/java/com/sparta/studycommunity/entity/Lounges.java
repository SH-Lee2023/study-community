package com.sparta.studycommunity.entity;

import com.sparta.studycommunity.dto.LoungesRequestDto;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lounges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String user_id;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long like_Count;

    @Column(nullable = false)
    private Timestamp create_at;

    public Lounges(LoungesRequestDto requestDto) {
        this.user_id = requestDto.getUser_id();
        this.contents = requestDto.getContents();
    }
}
