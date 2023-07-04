package com.sparta.studycommunity.repository;

import com.sparta.studycommunity.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
