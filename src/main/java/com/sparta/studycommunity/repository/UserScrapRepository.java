package com.sparta.studycommunity.repository;

import com.sparta.studycommunity.entity.Post;
import com.sparta.studycommunity.entity.User;
import com.sparta.studycommunity.entity.UserScrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserScrapRepository extends JpaRepository<UserScrap, Long> {
    Optional<UserScrap> findByPostAndUser(Post post, User user);
}
