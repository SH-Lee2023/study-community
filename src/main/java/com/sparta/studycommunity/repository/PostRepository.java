package com.sparta.studycommunity.repository;

import com.sparta.studycommunity.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByPostTagList_TagIdIn(List<Long> tagIds, Pageable pageable);
}
