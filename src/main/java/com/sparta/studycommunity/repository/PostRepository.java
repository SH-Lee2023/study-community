package com.sparta.studycommunity.repository;

import com.sparta.studycommunity.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
