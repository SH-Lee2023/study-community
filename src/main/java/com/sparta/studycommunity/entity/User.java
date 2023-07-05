package com.sparta.studycommunity.entity;

import com.sparta.studycommunity.dto.ProfileRequestDto;
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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String nickname;

    @Column(nullable = false)
    private String passwordDecoded;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) // Enum 타입을 db에 저장할때 사용하는 어노테이션
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    private List<UserScrap> scrapList;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade =  {CascadeType.ALL})
    private List<Post> postList = new ArrayList<>();

    public User(String username, String nickname, String passwordDecoded, String password, UserRoleEnum role) {
        this.username = username;
        this.nickname = nickname;
        this.passwordDecoded = passwordDecoded;
        this.password = password;
        this.role = role;
    }

    public void update(ProfileRequestDto profileRequestDto) {
        this.nickname = profileRequestDto.getNickname();
    }
}
