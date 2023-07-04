package com.sparta.studycommunity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String tag;

    @Column(nullable = false)
    private Integer scrapCount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<PostTag> postTagList = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    public Post(String title, String contents, String imageUrl, String tag, Integer scrapCount, User user) {
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.tag = tag;
        this.scrapCount = scrapCount;
        this.user = user;
    }
}