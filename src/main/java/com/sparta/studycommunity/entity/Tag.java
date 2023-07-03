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
@NoArgsConstructor
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<PostTag> postTagList = new ArrayList<>();

    public Tag(String str) {
        String name = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        this.name = name;
    }
}
