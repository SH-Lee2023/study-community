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
    private String userId;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long likeCount;

    @Column(nullable = false)
    private Timestamp createAt;

    public Lounges(LoungesRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.contents = requestDto.getContents();
    }
}
