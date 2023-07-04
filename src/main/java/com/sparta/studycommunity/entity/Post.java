package com.sparta.studycommunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contents;
    private String imageUrl;
    private String tag;
    private LocalDateTime createdAt;

    public Post(String title, String contents, String imageUrl, String tag) {
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.tag = tag;
        this.createdAt = LocalDateTime.now();

    }
}
